package com.kb038.soapapi.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "ArrayOfAcerCallStatus")
public class ArrayOfAcerCallStatus {
    private String xmlnsXsd;
    private String xmlns;
    private String xmlnsXsi;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "AcerCallStatus")
    private List<AcerCallStatus> acerCallStatus = new ArrayList<AcerCallStatus>();
    public String getXmlnsXsd() {
        return xmlnsXsd;
    }
    public void setXmlnsXsd(String xmlnsXsd) {
        this.xmlnsXsd = xmlnsXsd;
    }
    public String getXmlns() {
        return xmlns;
    }
    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }
    public String getXmlnsXsi() {
        return xmlnsXsi;
    }
    public void setXmlnsXsi(String xmlnsXsi) {
        this.xmlnsXsi = xmlnsXsi;
    }
    public List<AcerCallStatus> getAcerCallStatus() {
        return acerCallStatus;
    }
    public void setAcerCallStatus(List<AcerCallStatus> acerCallStatus) {
        this.acerCallStatus = acerCallStatus;
    }
}