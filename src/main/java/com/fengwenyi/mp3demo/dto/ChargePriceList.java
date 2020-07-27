package com.fengwenyi.mp3demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zhouliang
 * @Date: 2018/6/11 14:18
 */
@Data
public class ChargePriceList implements Serializable {
    @JsonProperty("shop_pay_price")
    private int shopPayPrice;
    @JsonProperty("charges_detail")
    private ChargesDetail chargesDetail;
    @JsonProperty("is_ladder")
    private int isLadder;
    @JsonProperty("is_floor")
    private int isFloor;
    @JsonProperty("is_dubious")
    private int isDubious;
    @JsonProperty("is_insured")
    private int isInsured;
    @JsonProperty("is_cashorder")
    private int isCashorder;
    @JsonProperty("is_big_order")
    private int isBigOrder;
    @JsonProperty("is_send_msg")
    private int isSendMsg;
}
