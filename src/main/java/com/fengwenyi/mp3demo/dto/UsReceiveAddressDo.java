package com.fengwenyi.mp3demo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * us_receive_address
 * @author
 */
public class UsReceiveAddressDo implements Serializable {
    private Long id;

    /**
     * ordId
     */
    private Long orgId;

    /**
     * ordCode
     */
    private String orgCode;

    /**
     * userId
     */
    private Long userId;

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

    /**
     * 1:已用 2:未用
     */
    private Integer type;

    /**
     * 状态(1可用 ，2删除)
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 收寄件区分1:收件(eg:标准下单)，2:寄件(eg:退货)
     */
    private Integer receiveSendType;

    /**
     * 来源类型1:扫码下单2:邀请填写
     */
    private Integer sourceType;

    /**
     * 下单类型,0, "标准下单",1, "异地代发",2, "批量下单"),3, "门店调拨",4, "客户退货";
     */
    private Integer orderType;

    /**
     * 微信用户openid
     */
    private String openId;

    /**
     * 组织路径编号
     */
    private String orgNumberPath;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReceiveCompany() {
        return receiveCompany;
    }

    public void setReceiveCompany(String receiveCompany) {
        this.receiveCompany = receiveCompany;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getReceiveSendType() {
        return receiveSendType;
    }

    public void setReceiveSendType(Integer receiveSendType) {
        this.receiveSendType = receiveSendType;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOrgNumberPath() {
        return orgNumberPath;
    }

    public void setOrgNumberPath(String orgNumberPath) {
        this.orgNumberPath = orgNumberPath;
    }
}
