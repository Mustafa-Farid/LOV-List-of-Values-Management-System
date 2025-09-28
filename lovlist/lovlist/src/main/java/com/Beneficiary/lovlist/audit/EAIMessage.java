package com.Beneficiary.lovlist.audit;


import com.Beneficiary.lovlist.logging.EAIHeader;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "EAIMessage")
@XmlAccessorType(XmlAccessType.FIELD)
public class EAIMessage {

    @XmlElement(name = "Flow_Tp")
    private String flowType;

    @XmlElement(name = "Flow_Id")
    private String flowId;

    @XmlElement(name = "AuditVars")
    private AuditVars auditVars;

    @XmlElement(name = "EAIHeader")
    private EAIHeader eaiHeader;

    @XmlElement(name = "FlowProperties")
    private FlowProperties flowProperties;

    public EAIMessage() {
    }

    public EAIMessage(String flowType, String flowId, AuditVars auditVars, EAIHeader eaiHeader, FlowProperties flowProperties) {
        this.flowType = flowType;
        this.flowId = flowId;
        this.auditVars = auditVars;
        this.eaiHeader = eaiHeader;
        this.flowProperties = flowProperties;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public AuditVars getAuditVars() {
        return auditVars;
    }

    public void setAuditVars(AuditVars auditVars) {
        this.auditVars = auditVars;
    }

    public EAIHeader getEaiHeader() {
        return eaiHeader;
    }

    public void setEaiHeader(EAIHeader eaiHeader) {
        this.eaiHeader = eaiHeader;
    }

    public FlowProperties getFlowProperties() {
        return flowProperties;
    }

    public void setFlowProperties(FlowProperties flowProperties) {
        this.flowProperties = flowProperties;
    }
}
