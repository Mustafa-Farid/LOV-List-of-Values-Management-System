package com.Beneficiary.lovlist.audit;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class FlowProperties {

    @XmlElement(name = "Audit_Flg")
    private int auditFlag;

    @XmlElement(name = "Dbg_Md")
    private int dbgMd;

    public FlowProperties(int auditFlag, int dbgMd) {
        this.auditFlag = auditFlag;
        this.dbgMd = dbgMd;
    }

    public int getAuditFlag() {
        return auditFlag;
    }

    public void setAuditFlag(int auditFlag) {
        this.auditFlag = auditFlag;
    }

    public int getDbgMd() {
        return dbgMd;
    }

    public void setDbgMd(int dbgMd) {
        this.dbgMd = dbgMd;
    }
}
