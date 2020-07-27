package com.fengwenyi.mp3demo.util;


import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

public class BspObjectUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //忽略在JSON字符串中存在，当时在Java对象中不存在属性的情况。防止错误
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    public static <T> T obj2Obj(Object obj,Class<T> clazz){
        return objectMapper.convertValue(obj,clazz);
    }

    public static <T> T list2ListObj(Object obj,Class<?> collectionClass,Class<?> elementClass){
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass,elementClass);
        return objectMapper.convertValue(obj,javaType);
    }
}
