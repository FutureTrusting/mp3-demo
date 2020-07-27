package com.fengwenyi.mp3demo.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import com.fengwenyi.mp3demo.config.BspConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class BspRequest {
    @JsonIgnore
    private final String MESSAGEHEAD = "<?xml version='1.0' encoding='UTF-8'?>";

    @JacksonXmlProperty(localName = "Head")
    private Head head;

    @JacksonXmlProperty(localName = "lang", isAttribute = true)
    private final String lang = "zh-CN";

    public BspRequest(BspConfig config) {
        this.head = new Head(config);
    }

    public BspRequest() {

    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class Head {
        @JsonIgnore
        private String checkword;
        // 接入编码
        @JacksonXmlText
        private String accessCode;

        public Head(BspConfig config) {
            this.accessCode = config.getAccessCode();
            this.checkword = config.getCheckword();
        }
    }

    @Setter
    @Getter
    public static class RequestBody {

    }
}
