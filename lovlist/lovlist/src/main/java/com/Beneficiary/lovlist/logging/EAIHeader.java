package com.Beneficiary.lovlist.logging;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class EAIHeader {
    @XmlElement(name = "MsgFrmt")
    private String msgFrmt;

    @XmlElement(name = "CIF")
    private String cif;

    @XmlElement(name = "Lang")
    private String lang;

    @XmlElement(name = "ChnlId")
    private String chnlId;

    @XmlElement(name = "FunctnId")
    private String functnId;

    @XmlElement(name = "FE_Ref_No")
    private String refNo;

    @XmlElement(name = "Tmstmp")
    private String tmstmp;

    @XmlElement(name = "EAIRtrnCd")
    private String rtrnCode;

    @XmlElement(name = "EAIRtrnCdDesc")
    private String rtrnDesc;

    // add other header fields if required

    public EAIHeader() {}

    public EAIHeader(String msgFrmt, String cif, String lang, String chnlId, String functnId, String refNo, String tmstmp, String rtrnCode, String rtrnDesc) {
        this.msgFrmt = msgFrmt;
        this.cif = cif;
        this.lang = lang;
        this.chnlId = chnlId;
        this.functnId = functnId;
        this.refNo = refNo;
        this.tmstmp = tmstmp;
        this.rtrnCode = rtrnCode;
        this.rtrnDesc = rtrnDesc;
    }

    // getters and setters...


    public String getMsgFrmt() {
        return msgFrmt;
    }

    public void setMsgFrmt(String msgFrmt) {
        this.msgFrmt = msgFrmt;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getChnlId() {
        return chnlId;
    }

    public void setChnlId(String chnlId) {
        this.chnlId = chnlId;
    }

    public String getFunctnId() {
        return functnId;
    }

    public void setFunctnId(String functnId) {
        this.functnId = functnId;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getTmstmp() {
        return tmstmp;
    }

    public void setTmstmp(String tmstmp) {
        this.tmstmp = tmstmp;
    }

    public String getRtrnCode() {
        return rtrnCode;
    }

    public void setRtrnCode(String rtrnCode) {
        this.rtrnCode = rtrnCode;
    }

    public String getRtrnDesc() {
        return rtrnDesc;
    }

    public void setRtrnDesc(String rtrnDesc) {
        this.rtrnDesc = rtrnDesc;
    }
}
