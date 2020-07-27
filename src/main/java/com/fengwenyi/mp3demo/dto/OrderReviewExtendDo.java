package com.fengwenyi.mp3demo.dto;

import lombok.*;

import java.util.Date;


/**
 * @Description:
 * @author: zhengfei
 * @param:
 * @date: 2019-03-05 18:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderReviewExtendDo extends UsOrderDo {
    private static final long serialVersionUID = 3154018029936231949L;

    private Date applyTime;

    private Date reviewTime;

    private String reviewRejectReason;

    private String applyUserName;

    private String applyUserTel;

    private Long applyOrgId;

    private String applyOrgCode;


    private String applyOrgName;

    private String applyOrgAddress;

    private String applyStoreCode;

    /**
     * 0:正常订单 1:批量下单 2:异地代发订单 3:调拨订单
     */
    private String orderTypeDesc;
    /**
     * 取件类型（0、一小时内 1、次日九点）
     */
    private String takePartTypeDesc;
    /**
     * ' 收件人门店类型（成本类型）收件人门店类型（成本类型）(0、个人 1、卖场 2、公司)'
     */
    private String recStoreTypeDesc;
    /**
     * '付款类型（0、寄付月结）',
     */
    private String paymentMethodDesc;
    /**
     * '产品类型（0、顺丰标快 1、顺丰特惠）',
     */
    private String productTypeDesc;
    /**
     * 订单状态（''订单状态 ：0待下单，1已下单，下运单 2分拣完|等待发货 3, "派送中"， 4, "已完成" ； -1, "退货" -2, "拒签"-3, "下单失败"-4, "下运单失败"'',
     */
    private String orderStatusDesc;
    /**
     * 寄件人门店名称
     */
    private String senderStoreName;
    /**
     * 收件人门店名称
     */
    private String recStoreName;
    /**
     * 品牌
     */
    private String brand;

    /**
     * 审核状态 '0：未审核 1：审核通过 2：驳回',
     */
    private String reviewStatusDesc;

    /***
     * 组织name
     */
    private String organizationName;
    /**
     * 审核状态
     */
    private Integer reviewStatus;
    /**
     * 审核userId
     */
    private String reviewUserId;
    /**
     * 审批项值typeId
     */
    private String reviewType;
    /**
     * 审批项值itemId
     */
    private String reviewItem;

    /**
     * 审批异常原因
     */
    private String reviewExceptional;
}
