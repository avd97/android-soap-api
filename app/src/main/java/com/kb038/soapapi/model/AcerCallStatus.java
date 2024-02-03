package com.kb038.soapapi.model;

public class AcerCallStatus {
    private String responseCode;
    private String statusName;
    private Integer id;
    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    public String getStatusName() {
        return statusName;
    }
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}