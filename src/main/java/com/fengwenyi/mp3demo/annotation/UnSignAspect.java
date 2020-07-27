package com.fengwenyi.mp3demo.annotation;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Caixin
 * @date 2019/8/2 9:56
 */
@Aspect
@Component
@Slf4j
@Order(2)
public class UnSignAspect {


    @Pointcut("@annotation(com.fengwenyi.mp3demo.annotation.UnSign)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object handlerControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.warn("UnSignAspect aspect start ");
        long timeMillis = System.currentTimeMillis();
        log.error("UnSignAspect Aspecting");

        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //2.最关键的一步:通过这获取到方法的所有参数名称的字符串数组
        String[] parameterNames = methodSignature.getParameterNames();
        //3.通过你需要获取的参数名称的下标获取到对应的值
        int orgId = ArrayUtils.indexOf(parameterNames, "orgId");
        if (orgId != -1) {
            long timeStamp = (Long) args[orgId];
            System.out.println("timeStamp" + timeStamp);
        } else {
            String arrayToString = argsArrayToString(args);
            String arrayToString22 = argsArrayToString22(args);
            System.out.println("arrayToString" + arrayToString);
            System.out.println("arrayToString22" + arrayToString22);
            JsonElement je = new JsonParser().parse(arrayToString);
            System.out.println(je.getAsJsonObject().get("orgId") + "<<<<<<<<<<<<<<orgId");
        }


//        Object[] args = joinPoint.getArgs();
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
//        Method method = methodSignature.getMethod();
//        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
//        assert args.length == parameterAnnotations.length;
//        int bound = args.length;
//        for (int argIndex = 0; argIndex < bound; argIndex++) {
//            for (Annotation annotation : parameterAnnotations[argIndex]) {
//                if (!(annotation instanceof RequestParam)) {
//                    continue;
//                }
//                RequestParam requestParam = (RequestParam) annotation;
//                if (!"orgId".equals(requestParam.value())) {
//                    //如果获取不到orgId,退出本次循环
//                    continue;
//                }
//                //获取orgId
//                System.out.println("获取orgId " + requestParam.value() + " = " + args[argIndex]);
//            }
//        }
        log.warn("UnSignAspect aspect time ====>>>>> 耗时：" + (System.currentTimeMillis() - timeMillis));

        Object proceed = joinPoint.proceed();
        log.warn("UnSignAspect  aspect end ");
        return proceed;
    }


    private String argsArrayToString22(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                Object jsonObj = JSON.toJSON(o);
                params.append(jsonObj.toString()).append(" ");
            }
        }
        return params.toString().trim();
    }

    private String argsArrayToString(Object[] paramsArray) {
        return Stream.of(paramsArray)
                .map(x -> {
                    StringBuilder params = new StringBuilder();
                    Object jsonObj = JSON.toJSON(x);
                    params.append(jsonObj.toString()).append(" ");
                    return params.toString().trim();
                }).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        //其实很简单，continue就是中断本次循环开始下一次。
        for (int i = 0; i <= 10; i++) {
            if (i < 9) {
                continue;
            }
            System.out.println(i);
        }
    }
}
