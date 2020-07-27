package com.fengwenyi.mp3demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zhouliang
 * @Date: 2018/6/11 14:17
 */
@Data
public class ChargesDetail implements Serializable {
    @JsonProperty("basic_fee")
    private int basicFee;
    private int basic;
    @JsonProperty("over_distance")
    private int overDistance;
    @JsonProperty("over_weight")
    private int overWeight;
    @JsonProperty("vas_fee")
    private int vasFee;
    @JsonProperty("special_time_fee")
    private int specialTimeFee;
}
