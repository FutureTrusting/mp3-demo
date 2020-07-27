package com.fengwenyi.mp3demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IDEA
 *
 * @author:WangXiaoShuai Date:2019/6/28
 * Time:17:38
 **/
@Data
public class CalcTimePromiseFreightRequest implements Serializable {

    /**
     * 寄件地址
     */
    private String srcAddress;

    /**
     * 目的地地址
     */
    private String destAddress;

    /**
     * 产品类型
     */
    private String expressType;

}
