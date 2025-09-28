package com.Beneficiary.lovlist.response;

import com.Beneficiary.lovlist.LOV.LOVList;

import java.util.List;

public class LOVListRes {

    private int status;
    private String message;
    private String timestamp;
    private List<LOVList> data;

    public LOVListRes(){}

    public LOVListRes(int status, String message, String timestamp, List<LOVList> data) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<LOVList> getData() {
        return data;
    }

    public void setData(List<LOVList> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CurrencyResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                '}';
    }
}
