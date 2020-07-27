//package com.fengwenyi.mp3demo.business;
//
//import com.fengwenyi.mp3demo.config.BspCommonRequest;
//import com.fengwenyi.mp3demo.config.BspConfig;
//import com.fengwenyi.mp3demo.config.BspRequest;
//import com.fengwenyi.mp3demo.enums.BspErrorCodeEnum;
//import com.fengwenyi.mp3demo.enums.BspUtils;
//import com.fengwenyi.mp3demo.enums.ErrorCodeException;
//import com.fengwenyi.mp3demo.enums.HttpClientUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//
///***
// * bsp下单抽象类
// * @author zhengfei
// * @date 2019-04-04 13:50
// */
//
//@Slf4j
//public abstract class BspBaseService {
//
//    @Autowired
//    private BspConfig bspConfig;
//
//    /**
//     * 传入对应的入参，返回请求丰桥接口返回对应的出参
//     */
//    public <T extends BspCommonRequest> Object service(T createOrderReq) {
//        BspConfig config = changeConfig(createOrderReq.getAccessCode(), createOrderReq.getCheckword());
//        checkBspConfig(config);
//        BspRequest bspRequest = buildRequest(config, createOrderReq);
//        long startTime = System.currentTimeMillis();
//        String responseXml = callSfExpressServiceByCSIM(bspRequest);
//        long endTime = System.currentTimeMillis();
//        log.info("bsp上运行该请求时间：{}毫秒,返回的内容是----->>>>>{}",endTime - startTime,responseXml);
//        Object obj = getBodyByReponse(responseXml);
//        return obj;
//    }
//
//    /**
//     * 检查传入的访问码和验证码
//     *
//     * @param accessCode
//     * @param checkword
//     * @return
//     */
//    public BspConfig changeConfig(String accessCode, String checkword) {
//        BspConfig config = new BspConfig();
//        if (StringUtils.isNotBlank(accessCode) && StringUtils.isNotBlank(checkword)) {
//            config.setAccessCode(accessCode);
//            config.setCheckword(checkword);
//        } else {
//            config.setAccessCode(bspConfig.getAccessCode());
//            config.setCheckword(bspConfig.getCheckword());
//        }
//        return config;
//    }
//
//    public void checkBspConfig(BspConfig config) {
//        if (StringUtils.isBlank(config.getAccessCode())) {
//            ErrorCodeException exception = BspErrorCodeEnum.ACCESSCODE_EMPTY.createException();
//            log.warn("BspBaseService BSP校验异常", exception);
//            throw exception;
//        }
//        if (StringUtils.isBlank(config.getCheckword())) {
//            ErrorCodeException exception = BspErrorCodeEnum.CHECKWORD_EMPTY.createException();
//            log.warn("BspBaseService BSP校验异常", exception);
//            throw exception;
//        }
//        if (StringUtils.isBlank(bspConfig.getUrl())) {
//            ErrorCodeException exception = BspErrorCodeEnum.BSP_REQUEST_URL_EMPTY.createException();
//            log.warn("BspBaseService BSP校验异常", exception);
//            throw exception;
//        }
//    }
//
//    /**
//     * 组合对应的Request
//     *
//     * @param config
//     * @param bspCommonRequest
//     * @return
//     */
//    public abstract BspRequest buildRequest(BspConfig config, BspCommonRequest bspCommonRequest);
//
//    /**
//     * 根据传入的request请求丰桥服务
//     *
//     * @param request
//     * @return
//     */
//    public String callSfExpressServiceByCSIM(BspRequest request) {
//        String requestXml = null;
//        try {
//            requestXml = BspUtils.toXml(request);
//            log.info("callSfExpressServiceByCSIM request:{}", requestXml);
//        } catch (Exception e) {
//            ErrorCodeException exception = BspErrorCodeEnum.REQUEST_OBJECT_TRANSFER_ERROR.createException();
//            exception.initCause(e);
//            log.warn("callSfExpressServiceByCSIM BSP组合成的对象转换为XML出错，原对象为：{}", request, e);
//            throw exception;
//        }
//        String checkWord = request.getHead().getCheckword();
//        String respXML = HttpClientUtils.callSfExpressServiceByCSIM(bspConfig.getUrl(), requestXml, checkWord);
////        log.info("callSfExpressServiceByCSIM url:{}, response:{}",bspConfig.getUrl(), respXML);
//        if (StringUtils.isBlank(respXML)) {
//            ErrorCodeException exception = BspErrorCodeEnum.BSP_CONNECT_TIMEOUT.createException();
//            log.warn("callSfExpressServiceByCSIM Bsp连接超时", exception);
//            throw exception;
//        }
//        return respXML;
//    }
//
//    /**
//     * 根据传入的xml获取到对应的返回值
//     *
//     * @param respXML
//     * @return
//     */
//    public abstract Object getBodyByReponse(String respXML);
//
//}
