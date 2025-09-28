package com.Beneficiary.lovlist.LOV;

import java.math.BigDecimal;

public class LOVList {

    private String lovCode;
    private String lovName;

    public LOVList() {}

    public LOVList(String lovCode, String lovName) {
        this.lovCode = lovCode;
        this.lovName = lovName;
    }

    public String getLovCode() {
        return lovCode;
    }

    public void setLovCode(String lovCode) {
        this.lovCode = lovCode;
    }

    public String getLovName() {
        return lovName;
    }

    public void setLovName(String lovName) {
        this.lovName = lovName;
    }

    @Override
    public String toString() {
        return "LOVList{" +
                "lovCode='" + lovCode + '\'' +
                ", lovName='" + lovName + '\'' +
                '}';
    }
}
