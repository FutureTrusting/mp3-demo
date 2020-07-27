package com.fengwenyi.mp3demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class BspUtils {
    private static XmlMapper xmlMapper = new XmlMapper();


    static {
        xmlMapper.setDefaultUseWrapper(false);
        // 字段为null，自动忽略，不再序列化
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // XML标签名:使用骆驼命名的属性名，
        xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        // 设置转换模式
        xmlMapper.enable(MapperFeature.USE_STD_BEAN_NAMING);
        //忽略大小写
        xmlMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        //针对不存在对应的属性不处理
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static String toXml(BspRequest request) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(request);
    }

    public static <T> T toBean(String text, Class<T> clazz) throws IOException {
        return xmlMapper.readValue(text, clazz);
    }

    public static <T> T toBeanByBsp(String text, Class<T> clazz) throws Exception {
        return xmlMapper.readValue(text, clazz);
    }

    public static String toXmlByBsp(Object value) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(value);
    }

}
