package com.fengwenyi.mp3demo.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class BspConfig {

    @Value("${bsp.url}")
    private String url;

    private String accessCode;

    private String checkword;

}
