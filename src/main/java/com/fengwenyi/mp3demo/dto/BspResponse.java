package com.fengwenyi.mp3demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BspResponse {
    //  响应成功
    public static final String OK = "OK";
    //  响应失败
    public static final String ERR = "ERR";


    @JacksonXmlProperty(localName = "Head")
    private String head;


    @JacksonXmlProperty(localName = "service", isAttribute = true)
    private String service;

    @JacksonXmlProperty(localName = "ERROR")
    private BspError error;


    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BspError {

        @JacksonXmlProperty(localName = "code", isAttribute = true)
        private String code;

        @JacksonXmlText
        private String message;

        public BspError() {

        }
    }

    public static abstract class ResponseBody {

    }

    public static String getOK() {
        return OK;
    }

    public static String getERR() {
        return ERR;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public BspError getError() {
        return error;
    }

    public void setError(BspError error) {
        this.error = error;
    }
}
