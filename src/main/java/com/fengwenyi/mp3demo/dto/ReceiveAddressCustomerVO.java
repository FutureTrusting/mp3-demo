package com.fengwenyi.mp3demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : Caixin
 * @date 2019/9/17 14:08
 */

@Data
public class ReceiveAddressCustomerVO implements Serializable {
    /**
     * 收/寄件公司
     */
    private String receiveCompany;

    /**
     * 收/寄件人姓名
     */
    private String receiveUserName;

    /**
     * 收/寄件人电话
     */
    private String receivePhone;

    /**
     * 收/寄件人地址
     */
    private String receiveAddress;

    /**
     * 备注(寄件时为空)
     */
    private String remark;

    private static final long serialVersionUID = -8083775187618424986L;
}
