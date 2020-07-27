package com.fengwenyi.mp3demo.controller;

import cn.hutool.core.util.StrUtil;
import com.fengwenyi.mp3demo.dto.*;
import com.fengwenyi.mp3demo.enums.BspRlsCodeEnum;
//import com.fengwenyi.mp3demo.idworker.IdWorkerService;
import com.fengwenyi.mp3demo.util.BspObjectUtils;
import com.fengwenyi.mp3demo.util.BspUtils;
import com.fengwenyi.mp3demo.util.MailNoUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : Caixin
 * @date 2019/10/15 11:35
 */

@Slf4j
@RestController
public class CreateOrderController {

//    @Autowired
//    private IdWorkerService idWorkerService;

    public static void main(String[] args) {
        long orderNo=292840918752612352L;
        String responseXml = "<?xml version='1.0' encoding='UTF-8'?><Response service=\"OrderService\"><Head>OK</Head><Body><OrderResponse filter_result=\"2\" destcode=\"712\" mailno=\"234817503960\" orderid=\"292840918752612352\"><rls_info rls_errormsg=\"234817503960:必填字段为空!\" invoke_result=\"OK\" rls_code=\"0004\"/></OrderResponse></Body></Response>";
        BspOrderResponse resp = (BspOrderResponse) getBodyByResponse(responseXml);
        System.err.println(new Gson().toJson(resp));
        // 判断调用结果是否下单成功
        boolean respNull = resp != null && StringUtils.isNotBlank(resp.getOrderNumber()) && StringUtils.isNotBlank(resp.getMailNumber()) && !CollectionUtils.isEmpty(resp.getRlsInfo());
        // 下单成功 更新订单状态，运单号
        boolean success = respNull && StringUtils.equals(resp.getRlsInfo().get(0).getInvokeResult(), BspReponse.OK)
                && (StringUtils.equals(resp.getRlsInfo().get(0).getRlsCode(), BspRlsCodeEnum.SUCCESS.getCode()) || StringUtils.equals(resp.getRlsInfo().get(0).getRlsCode(), BspRlsCodeEnum.GET_ROUTE_ERROR.getCode()));
        System.err.println(success);
        if (success) {
            saveMailInfo(buildUsMailDo(resp, orderNo));
        }
    }

    private static boolean saveMailInfo(List<UsMailDo> usMailDos) {
        if (CollectionUtils.isEmpty(usMailDos)) {
            return false;
        }
        System.err.println("saveMailInfo usMailDos" + new Gson().toJson(usMailDos));
        return Boolean.TRUE;
    }


    private static Object getBodyByResponse(String respXml) {
        BspOrderServiceResponse response = null;
        try {
            response = BspUtils.toBean(respXml, BspOrderServiceResponse.class);
        } catch (IOException e) {
            log.info("bspCreateOrder BSP对象转换异常入参对象为：{}", e.getMessage());
        }
        if (StringUtils.equals(response.getHead(), BspResponse.ERR)) {
            if (StringUtils.equals(response.getError().getCode(), "8027")) {
                log.info("bspCreateOrder bsp错误编码：{}", response.getError().getCode());
            } else {
                log.info("bspCreateOrder bsp错误编码：{}", response.getError().getCode());
            }
        }
        return BspObjectUtils.obj2Obj(response.getBody().get(0), BspOrderResponse.class);
    }

    private static List<UsMailDo> buildUsMailDo(BspOrderResponse resp, Long orderNo) {
        if (resp == null || CollectionUtils.isEmpty(resp.getRlsInfo())) {
            return Collections.emptyList();
        }
        List<UsMailDo> dos = new ArrayList<>();



        for (BspOrderResponse.BspRlsInfo rlsInfo : resp.getRlsInfo()) {
            List<BspOrderResponse.BspRlsDetail> rlsDetail = rlsInfo.getRlsDetail();
            if (CollectionUtils.isEmpty(rlsDetail)) {
                //特殊情况 rls_code="0004"
                //<?xml version='1.0' encoding='UTF-8'?><Response service="OrderService"><Head>OK</Head><Body><OrderResponse filter_result="2" destcode="712" mailno="234817503960" orderid="292840918752612352"><rls_info rls_errormsg="234817503960:必填字段为空!" invoke_result="OK" rls_code="0004"/></OrderResponse></Body></Response>
                if(StrUtil.isNotBlank(resp.getDestCode()) && StrUtil.isNotBlank(resp.getMailNumber())){
                    MailNoDTO mailNoDTO = MailNoUtil.splitMailNo(resp.getMailNumber());
                    UsMailDo mailDoNoDetail = new UsMailDo();
                    mailDoNoDetail.setWaybillNo(mailNoDTO.getMainMailNo());
                    mailDoNoDetail.setDestcode(resp.getDestCode());
                    mailDoNoDetail.setOrderNo(orderNo);
                    dos.add(mailDoNoDetail);
                }
                continue;
            }
            BspOrderResponse.BspRlsDetail detail = rlsDetail.get(0);
            UsMailDo mailDo = new UsMailDo();
            MailNoDTO mailNoDTO = MailNoUtil.splitMailNo(detail.getWaybillNumber());
            mailDo.setWaybillNo(mailNoDTO.getMainMailNo());
            mailDo.setSourceTransferCode(detail.getSourceTransferCode());
            mailDo.setSourceCityCode(detail.getSourceCityCode());
            mailDo.setSourceDeptCode(detail.getSourceDeptCode());
            mailDo.setSourceTeamCode(detail.getSourceTeamCode());
            mailDo.setDestCityCode(detail.getDestCityCode());
            mailDo.setDestDeptCode(detail.getDestDeptCode());
            mailDo.setDestDeptCodeMapping(detail.getDestDeptCodeMapping());
            mailDo.setDestTeamCode(detail.getDestTeamCode());
            mailDo.setDestRouteLabel(detail.getDestRouteLabel());
            mailDo.setProName(detail.getProName());
            mailDo.setCargoTypeCode(detail.getCargoTypeCode());
            mailDo.setLimitTypeCode(detail.getLimitTypeCode());
            mailDo.setExpressTypeCode(detail.getExpressTypeCode());
            mailDo.setCodingMapping(detail.getCodingMapping());
            mailDo.setDestDeptCodeMapping(detail.getDestDeptCodeMapping());
            mailDo.setXbFlag(detail.getXbFlag());
            mailDo.setPrintFlag(detail.getPrintFlag());
            mailDo.setTwoDimensionCode(detail.getTwoDimensionCode());
            mailDo.setProCode(detail.getProCode());
            mailDo.setPrintIcon(detail.getPrintIcon());

            mailDo.setOrderNo(orderNo);
            mailDo.setMailno(resp.getMailNumber());
            mailDo.setFilterResult(resp.getFilterResult().toString());
            mailDo.setDestcode(resp.getDestCode());
            mailDo.setOrigincode(resp.getOriginCode());

            mailDo.setRlsCode(rlsInfo.getRlsCode());
            mailDo.setRlsErrormsg(rlsInfo.getErrorDesc());
            mailDo.setInvokeResult(rlsInfo.getInvokeResult());

            //AB 标
            mailDo.setAbFlag(detail.getAbFlag());
            //目的地口岸代码
            mailDo.setDestPortCode(detail.getDestPortCode());
            //目的国别(国别代码如：JP)
            mailDo.setDestCountry(detail.getDestCountry());
            //目的地邮编
            mailDo.setDestPostCode(detail.getDestPostCode());
            //总价值(保留两位小数,数字类型,可补位)
            mailDo.setGoodsValueTotal(detail.getGoodsValueTotal());
            //币种
            mailDo.setCurrencySymbol(detail.getCurrencySymbol());
            //件数
            mailDo.setGoodsNumber(detail.getGoodsNumber());
            //国际件图标
            mailDo.setProIcon(detail.getProIcon());
            //国际件图标
            mailDo.setFileIcon(detail.getFileIcon());
            //国际件图标
            mailDo.setFbaIcon(detail.getFbaIcon());
            //国际件图标
            mailDo.setIcsmIcon(detail.getIcsmIcon());

            dos.add(mailDo);
        }
        return dos;
    }
}
