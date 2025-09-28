package com.Beneficiary.lovlist.service;

import com.ibm.mq.*;
import com.ibm.mq.constants.CMQC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Service
public class MQNativeProducer {

    @Value("${ibm.mq.queueManager}")
    private String QMGR;

    @Value("${ibm.mq.host}")
    private String HOST;

    @Value("${ibm.mq.port}")
    private int PORT;

    @Value("${ibm.mq.channel}")
    private String CHANNEL;

    @Value("${ibm.mq.user}")
    private String USER;

    @Value("${ibm.mq.password}")
    private String PASSWORD;

    private MQQueueManager queueManager;
    private boolean initialized = false;

    private synchronized void initializeConnection() throws MQException {
        if (!initialized) {
            MQEnvironment.hostname = HOST;
            MQEnvironment.channel = CHANNEL;
            MQEnvironment.port = PORT;
            MQEnvironment.userID = USER;
            MQEnvironment.password = PASSWORD;
            MQEnvironment.properties.put(CMQC.TRANSPORT_PROPERTY, CMQC.TRANSPORT_MQSERIES_CLIENT);

            queueManager = new MQQueueManager(QMGR);
            initialized = true;
        }
    }

    @Async
    public void sendMessageAsync(String messageContent, String correlationId, String destQueue) {
        try {
            sendMessage(messageContent, correlationId, destQueue);
        } catch (Exception e) {
            // Log the error instead of throwing to avoid interrupting the main thread
            System.err.println("Async MQ send failed: " + e.getMessage());
        }
    }

    public void sendMessage(String messageContent, String correlationId, String destQueue) throws MQException, IOException {
        if (!initialized) {
            initializeConnection();
        }

        MQQueue queue = null;
        try {
            int openOptions = CMQC.MQOO_OUTPUT | CMQC.MQOO_FAIL_IF_QUIESCING;
            queue = queueManager.accessQueue(destQueue, openOptions);

            MQMessage mqMessage = new MQMessage();
            mqMessage.characterSet = 1089;
            mqMessage.encoding = 546;
            mqMessage.persistence = CMQC.MQPER_NOT_PERSISTENT;

            byte[] correlationIdBytes = hexStringToByteArray(correlationId);
            mqMessage.messageId = correlationIdBytes;
            mqMessage.correlationId = correlationIdBytes;
            mqMessage.putApplicationName = "CurrencySP";

            mqMessage.writeString(messageContent);

            MQPutMessageOptions pmo = new MQPutMessageOptions();
            pmo.options = CMQC.MQPMO_FAIL_IF_QUIESCING | CMQC.MQPMO_SYNCPOINT;

            queue.put(mqMessage, pmo);
            queueManager.commit(); // Explicitly commit after put

        } catch (MQException e) {
            handleMQException(e);
            throw e;
        } finally {
            closeQueue(queue);
        }
    }

    private void handleMQException(MQException e) throws MQException {
        initialized = false;
        throw new MQException(e.completionCode, e.reasonCode, "MQ operation failed", e);
    }

    private void closeQueue(MQQueue queue) {
        if (queue != null) {
            try {
                queue.close();
            } catch (MQException e) {
                System.err.println("Warning: Failed to close queue: " + e.getMessage());
            }
        }
    }

    @PreDestroy
    public void cleanup() {
        if (queueManager != null) {
            try {
                queueManager.disconnect();
            } catch (MQException e) {
                System.err.println("Warning: Failed to disconnect queue manager: " + e.getMessage());
            }
        }
    }

    private byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
}

