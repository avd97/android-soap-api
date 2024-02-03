package com.kb038.soapapi.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AcerCallStatus {

    @JacksonXmlProperty(localName = "ResponseCode")
    private String responseCode;
    @JacksonXmlProperty(localName = "StatusName")
    private String statusName;
    @JacksonXmlProperty(localName = "Id")
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