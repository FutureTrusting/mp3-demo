package com.fengwenyi.mp3demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zhouliang
 * @Date: 2018/6/11 14:13
 */
@Data
public class PrecreateOrderRes implements Serializable {
    /**
     * 0：预约送达单 1：立即单 3：预约上门单
     */
    @JsonProperty("delivery_type")
    private int deliveryType;
    /**
     * 预计送达（上门）时间
     */
    @JsonProperty("expect_time")
    private int expectTime;
    /**
     * 预计开始配送的时间
     */
    @JsonProperty("start_time")
    private int startTime;
    /**
     * 预计配送时间（单位: 分）
     */
    @JsonProperty("promise_delivery_time")
    private int promiseDeliveryTime;

    @JsonProperty("charge_price_list")
    private ChargePriceList chargePriceList;
    /**
     * 订单小费
     */
    @JsonProperty("gratuity_fee")
    private int gratuityFee;
    /**
     * 推单时间
     */
    @JsonProperty("push_time")
    private int pushTime;
}
