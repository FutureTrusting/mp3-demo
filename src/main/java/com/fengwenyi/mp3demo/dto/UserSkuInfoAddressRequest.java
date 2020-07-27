package com.fengwenyi.mp3demo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: LiuRenjin
 * @create: 2019-04-23 15:16
 **/
@Data
public class UserSkuInfoAddressRequest {
    /**
     * 商品列表
     */
    private List<UsSkuIdNumRequest> skuInfo;

    /**
     * 收获人姓名
     */
    @Length(max = 50, message = "收件人姓名最大50字符")
    private String receiveUserName;

    /**
     * 收获人电话
     */
    @Length(max = 200, message = "电话最大200字符")
    private String receivePhone;

    /**
     * 收获人地址
     */
    @Length(max = 1000, message = "收件地址最大1000字符")
    private String receiveAddress;

    /**
     * 备注
     */
    @Length(max = 100, message = "备注信息最大100字符")
    private String remark;

    /**
     * 用户id
     */
    @NotBlank(message = "用户id 不能为空")
    private String userId;

    /**
     * 组织id
     */
    @NotBlank(message = "组织id 不能为空")
    private String orgId;

    /**
     * 收寄类型 1:收件(eg:标准下单)，2:寄件(eg:退货)
     */
    @NotNull(message = "收寄类型 不能为空")
    private Integer receiveSendType=1;

    /**
     * 来源类型 1:扫码下单2:邀请填写
     */
    @NotNull(message = "来源类型 不能为空")
    private Integer sourceType=1;

    /**
     * 下单类型,0, "标准下单",1, "异地代发",2, "批量下单"),3, "门店调拨",4, "客户退货";
     */
    private Integer orderType=1;

    public static void main(String[] args) {
        UserSkuInfoAddressRequest request = new UserSkuInfoAddressRequest();
        request.setOrgId("1111");
        System.err.println(request.toString());
    }

}
