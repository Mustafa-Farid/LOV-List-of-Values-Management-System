package com.Beneficiary.lovlist.controller;

import com.Beneficiary.lovlist.LOV.LOVList;
import com.Beneficiary.lovlist.LOV.LOVListSPR;
import com.Beneficiary.lovlist.exceptionHandling.GlobalExceptionHandling;
import com.Beneficiary.lovlist.response.LOVListRes;
import com.Beneficiary.lovlist.service.MQNativeProducer;
import com.Beneficiary.lovlist.logging.DumpMsg;
import com.Beneficiary.lovlist.logging.EAIHeader;
import com.Beneficiary.lovlist.logging.MsgData;
import com.Beneficiary.lovlist.logging.RequestBody;
import com.Beneficiary.lovlist.logging.ResponseBody;
import com.Beneficiary.lovlist.service.LOVService;
import com.Beneficiary.lovlist.utils.XmlUtils;
import com.Beneficiary.lovlist.audit.AuditVars;
import com.Beneficiary.lovlist.audit.EAIMessage;
import com.Beneficiary.lovlist.audit.FlowProperties;

import com.ibm.mq.MQException;

import javax.jms.JMSException;
import javax.xml.bind.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/lov")
public class RESTController {
    @Value("${ibm.mq.queueDump}")
    private String dumpQueue;

    @Value("${ibm.mq.queueAudit}")
    private String auditQueue;

    @Value("${app.msgFrmt}")
    private String messageFormat;

    @Value("${app.cifNo}")
    private String cifNumber;

    @Value("${app.lang}")
    private String language;

    @Value("${app.channel}")
    private String appChannel;

    @Value("${app.functionId}")
    private String appFunctionId;

    @Value("${app.flowId}")
    private String appFlowId;

    @Value("${app.auditFlag}")
    private int auditFlag;

    @Value("${app.dbgMd}")
    private int dbgMd;

    @Value("${app.Flow_Tp}")
    private String flowTp;

    @Autowired
    private LOVService lovService;

    @Autowired
    private MQNativeProducer mqNativeProducer;

    @GetMapping("/{lovCode}")
    public ResponseEntity<LOVListRes> getLOVList(@PathVariable int lovCode) throws JAXBException, JMSException, MQException, GlobalExceptionHandling {

        String msgId = generateMessageId();
        LOVListSPR lovListSPR;
        // Common parameters
        String msgFrmt = messageFormat;
        String cifNo = cifNumber;
        String lang = language;
        String channel = appChannel;
        String functionId = appFunctionId;
        String refNo = generateFtNumber();
        String flowId = appFlowId;

        String rtrnCode;
        String rtrnDesc;

        // Request message
        logReq(lovCode,msgFrmt,cifNo,lang,channel,functionId,refNo,flowId,msgId);

        // Business logic
        lovListSPR = lovService.getLovList(lovCode);


        // Response message
        if("000000".equals(lovListSPR.getStatus())) {
            rtrnCode = "000000";
            rtrnDesc = "SUCCESS";
        }
        else {
            rtrnCode = "878787";
            rtrnDesc = "Procedure Error";
        }

        logRes( lovListSPR.getLovList(),msgFrmt,cifNo,lang,channel,functionId,refNo,flowId,msgId,rtrnCode,rtrnDesc);

        //Auditing
        audit(msgFrmt,cifNo,lang,channel,functionId,refNo,flowId,msgId,rtrnCode,rtrnDesc);


        //Http Response
        return setResponse("SUCCESS",HttpStatus.OK,lovListSPR.getLovList());
    }





    private void logReq(int lovCode, String msgFrmt, String cifNo, String lang, String channel, String functionId, String refNo, String flowId, String msgId) throws JAXBException {
        RequestBody requestBody = new RequestBody(lovCode);
        String timStamp1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        EAIHeader eaiHeader1 = new EAIHeader(msgFrmt, cifNo, lang, channel, functionId, refNo, timStamp1, null, null);
        MsgData msgData1 = new MsgData(requestBody, null, eaiHeader1);
        DumpMsg dumpMessage = new DumpMsg(msgData1, "REQ", flowId, msgId, cifNo);
        String xmlPayload = XmlUtils.marshal(dumpMessage);
        mqNativeProducer.sendMessageAsync(xmlPayload, msgId, dumpQueue);
    }

    private void logRes(List<LOVList> lovList, String msgFrmt, String cifNo, String lang, String channel, String functionId, String refNo, String flowId, String msgId, String rtrnCode, String rtrnDesc) throws JAXBException {
        // Response message
        ResponseBody responseBody = new ResponseBody(lovList);
        String timStamp2 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        EAIHeader eaiHeader2 = new EAIHeader(msgFrmt, cifNo, lang, channel, functionId, refNo, timStamp2, rtrnCode, rtrnDesc);
        MsgData msgData2 = new MsgData(null, responseBody, eaiHeader2);
        DumpMsg dumpMessage2 = new DumpMsg(msgData2, "RPLY", flowId, msgId, cifNo);
        String xmlPayload2 = XmlUtils.marshal(dumpMessage2);
        mqNativeProducer.sendMessageAsync(xmlPayload2, msgId, dumpQueue);
    }

    private void audit(String msgFrmt, String cifNo, String lang, String channel, String functionId, String refNo, String flowId, String msgId, String rtrnCode, String rtrnDesc) throws JAXBException {
        String putTmstmp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));
        String tmstmp1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        String tmstmp2 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        AuditVars auditVars = new AuditVars(putTmstmp, tmstmp1, tmstmp2);
        EAIHeader auditHeader = new EAIHeader(msgFrmt, cifNo, lang, channel, functionId, refNo, null, rtrnCode, rtrnDesc);
        FlowProperties flowProperties = new FlowProperties(auditFlag, dbgMd);
        EAIMessage auditMsg = new EAIMessage(flowTp, flowId, auditVars, auditHeader, flowProperties);
        String auditMsgString = XmlUtils.marshal(auditMsg);
        mqNativeProducer.sendMessageAsync(auditMsgString, msgId, auditQueue);
    }

    private ResponseEntity<LOVListRes> setResponse(String message, HttpStatus status, List<LOVList>lovList){
        LOVListRes response = new LOVListRes();
        response.setStatus(status.value());
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")));
        response.setData(lovList);
        return new ResponseEntity<>(response, status);
    }

    private String generateMessageId() {
        return "414d512042424541494c583033532020" + // AMQ + QMGR prefix
                UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }

    private String generateFtNumber() {
        return "FT" +
                ThreadLocalRandom.current().nextLong(1_000_000_000L,10_000_000_000L);
    }
}
