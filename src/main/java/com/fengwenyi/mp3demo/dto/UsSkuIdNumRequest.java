package com.fengwenyi.mp3demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 01376494
 */
@Data
public class UsSkuIdNumRequest implements Serializable {
    private static final long serialVersionUID = -3708352301084297340L;
    /**
     * 商品Id
     */
    private String skuId;
    /**
     * 商品数量
     */
    private String skuNum;
}
