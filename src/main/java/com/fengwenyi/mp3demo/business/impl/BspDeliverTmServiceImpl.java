//package com.fengwenyi.mp3demo.business.impl;
//
//import com.fengwenyi.mp3demo.business.BspBaseService;
//import com.fengwenyi.mp3demo.config.*;
//import com.fengwenyi.mp3demo.enums.BspErrorCodeEnum;
//import com.fengwenyi.mp3demo.enums.BspUtils;
//import com.fengwenyi.mp3demo.enums.ErrorCodeException;
//import com.fengwenyi.mp3demo.util.BspObjectUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.Arrays;
//
///**
// * Created with IDEA
// *
// * @author:WangXiaoShuai Date:2019/7/4
// * Time:9:36
// **/
//@Service
//@Slf4j
//public class BspDeliverTmServiceImpl extends BspBaseService {
//    @Override
//    public BspRequest buildRequest(BspConfig config, BspCommonRequest bspCommonRequest) {
//        BspDeliverTmServiceRequest request = new BspDeliverTmServiceRequest(config);
//        BspDeliverTmServiceRequest.DeliverTm route = BspObjectUtils.obj2Obj(bspCommonRequest, BspDeliverTmServiceRequest.DeliverTm.class);
//        request.setBody(Arrays.asList(route));
//        return request;
//    }
//
//    @Override
//    public Object getBodyByReponse(String respXML) {
//        BspDeliverTmServiceResponse response = null;
//        try {
//            response = BspUtils.toBean(respXML, BspDeliverTmServiceResponse.class);
//        } catch (IOException e) {
//            ErrorCodeException exception = BspErrorCodeEnum.RESPONSE_OBJECT_TRANSFER_ERROR.createException();
//            exception.initCause(e);
//            log.warn("BSP对象转换异常入参对象为：{}", response, e);
//            throw exception;
//        }
//        if (StringUtils.equals(response.getHead(), BspResponse.ERR)) {
//            ErrorCodeException exception = BspErrorCodeEnum.BSP_OPERATION_FAILED.createException(response.getError().getMessage());
//            log.warn("bsp错误编码：{}", response.getError().getCode(), exception);
//            throw exception;
//        }
//        BspDeliverTmResponse result = BspObjectUtils.obj2Obj(response.getBody().get(0), BspDeliverTmResponse.class);
//        return result;
//    }
//}
