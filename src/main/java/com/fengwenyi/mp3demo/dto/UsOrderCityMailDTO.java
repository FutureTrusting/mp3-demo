package com.fengwenyi.mp3demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 01376494
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsOrderCityMailDTO implements Serializable {
    private Long id;
    /**
     * 订单号
     */
    private Long orderNo;
    /**
     * 顺丰运单号
     */
    private String mailNo;
    /**
     * 配送员姓名
     */
    private String deliveryName;

    /**
     * 配送员电话
     */
    private String deliveryPhone;





    /**
     * 收件人详细地址
     */
    private String recPersonAddress;
    /**
     * 取货序号
     */
    private String orderSequence;
    /**
     * 订单来源1,"后台管理批量下单";2,"小程序下单"；3,"后台管理单条下单"；4,"开放平台"
     */
    private Integer orderSource;
    /**
     * 订单状态（'订单状态 ：0，已导入，1，已下单，2，顺丰已收件 3, 运输中， 4, 派送中 , 5，已签收 6，异常件）
     */
    private Integer orderStatus;
    /**
     * 收件人姓名
     */
    private String recPerson;
    /**
     * 收件人电话
     */
    private String recPersonTel;
    /**
     * 签收时间
     */
    private Date signTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 客户订单号
     */
    private String custOrderNo;
    private static final long serialVersionUID = 4047082476075654414L;
}
