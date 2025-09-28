package com.Beneficiary.lovlist.logging;

import com.Beneficiary.lovlist.LOV.LOVList;
import java.util.List;

public class ResponseBody {
    private List<LOVList> lovLists;  // Changed from single MTOList to List

    public ResponseBody(List<LOVList> lovLists) {
        this.lovLists = lovLists;
    }

    public List<LOVList> getLovLists() {
        return lovLists;
    }

    public void setLovLists(List<LOVList> lovLists) {
        this.lovLists = lovLists;
    }
}