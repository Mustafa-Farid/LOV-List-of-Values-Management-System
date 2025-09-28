package com.Beneficiary.lovlist.logging;

public class RequestBody {


    private int lovCode;

    public RequestBody(int lovCode) {
        this.lovCode = lovCode;
    }

    public int getLovCode() {
        return lovCode;
    }

    public void setLovCode(int lovCode) {
        this.lovCode = lovCode;
    }
}
