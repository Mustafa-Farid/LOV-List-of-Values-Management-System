package com.Beneficiary.lovlist.logging;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "EAIMessage")
@XmlAccessorType(XmlAccessType.FIELD)
public class MsgData {

    @XmlElement
    private RequestBody requestBody;

    @XmlElement
    private ResponseBody responseBody;

    @XmlElement(name = "EAIHeader")
    private EAIHeader eaiHeader;

    public MsgData() {}

    public MsgData(RequestBody requestBody, ResponseBody responseBody, EAIHeader eaiHeader) {
        this.requestBody = requestBody;
        this.responseBody = responseBody;
        this.eaiHeader = eaiHeader;
    }

    // getters and setters...


    public RequestBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public EAIHeader getEaiHeader() {
        return eaiHeader;
    }

    public void setEaiHeader(EAIHeader eaiHeader) {
        this.eaiHeader = eaiHeader;
    }
}
