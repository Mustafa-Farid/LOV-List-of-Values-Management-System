package com.Beneficiary.lovlist.LOV;

import java.util.List;

public class LOVListSPR {

    private String status;

    private List<LOVList> lovList;

    public LOVListSPR(){}

    public LOVListSPR(String status, List<LOVList> lovList) {
        this.status = status;
        this.lovList = lovList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LOVList> getLovList() {
        return lovList;
    }

    public void setLovList(List<LOVList> lovList) {
        this.lovList = lovList;
    }

    @Override
    public String toString() {
        return "LOVListSPR{" +
                "status='" + status + '\'' +
                ", lovList=" + lovList +
                '}';
    }
}
