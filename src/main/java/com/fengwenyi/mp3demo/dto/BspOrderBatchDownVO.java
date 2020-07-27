package com.fengwenyi.mp3demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * created by liurenjin on 2019/1/8
 * @author happyGGQ
 */
@Data
@ApiModel(value = "批量新增 下单返回")
public class BspOrderBatchDownVO {

    private UsOrderDo usOrderDo;

    private Boolean reviewValidFlag;

    @ApiModelProperty("成功数")
    private Integer successNum;
    @ApiModelProperty("失败数")
    private Integer errorNum;

    private Integer reviewedNum;
    private Integer orderedNum;

    private OrderReviewTypeEnum orderReviewTypeEnum;

    /***
     * 订单审核类型
     * @author zhengfei
     * @date 2019-03-13 20:23
     */
    private Integer reviewType;
    /**
     * 批量下单不需要审核_下单成功的主运单号
     */
    private List<String> mainMailNos;

}
