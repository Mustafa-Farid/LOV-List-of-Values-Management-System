package com.Beneficiary.lovlist.audit;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class AuditVars {
    @XmlElement(name = "Put_Tmstmp")
    private String putTmstmp;

    @XmlElement(name = "Tmstmp_1")
    private String tmstmp1;

    @XmlElement(name = "Tmstmp_4")
    private String tmstmp4;

    public AuditVars(String putTmstmp, String tmstmp1, String tmstmp4) {
        this.putTmstmp = putTmstmp;
        this.tmstmp1 = tmstmp1;
        this.tmstmp4 = tmstmp4;
    }

    public String getPutTmstmp() {
        return putTmstmp;
    }

    public void setPutTmstmp(String putTmstmp) {
        this.putTmstmp = putTmstmp;
    }

    public String getTmstmp1() {
        return tmstmp1;
    }

    public void setTmstmp1(String tmstmp1) {
        this.tmstmp1 = tmstmp1;
    }

    public String getTmstmp4() {
        return tmstmp4;
    }

    public void setTmstmp4(String tmstmp4) {
        this.tmstmp4 = tmstmp4;
    }
}
