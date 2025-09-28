package com.Beneficiary.lovlist.logging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@XmlRootElement(name = "DumpMessage")
@XmlAccessorType(XmlAccessType.FIELD)
public class DumpMsg {

    @XmlElement(name = "Msg_Data")
    private MsgData msgData;

    @XmlElement(name = "Dump_Msg_Tp")
    private String dumpMsgTp;

    @XmlElement(name = "Flow_Id")
    private String flowId;

    @XmlElement(name = "MsgId")
    private String msgId;

    @XmlElement(name = "CIF_No")
    private String cifNo;

    @XmlElement(name = "TimeStamp")
    private String timeStamp;

    // Constructors, getters, setters

    public DumpMsg() {}

    public DumpMsg(MsgData msgData, String dumpMsgTp, String flowId, String msgId, String cifNo) {
        this.msgData = msgData;
        this.dumpMsgTp = dumpMsgTp;
        this.flowId = flowId;
        this.msgId = msgId;
        this.cifNo = cifNo;
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
    }

    // getters and setters...


    public MsgData getMsgData() {
        return msgData;
    }

    public void setMsgData(MsgData msgData) {
        this.msgData = msgData;
    }

    public String getDumpMsgTp() {
        return dumpMsgTp;
    }

    public void setDumpMsgTp(String dumpMsgTp) {
        this.dumpMsgTp = dumpMsgTp;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getCifNo() {
        return cifNo;
    }

    public void setCifNo(String cifNo) {
        this.cifNo = cifNo;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}

