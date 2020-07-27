package com.fengwenyi.mp3demo.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * us_order_review
 * @author 
 */
public class UsOrderReviewDo implements Serializable {
    private Long id;

    /**
     * 订单no
     */
    private Long orderNo;

    /**
     * 0：未审核 1：审核通过 2：驳回
     */
    private Integer reviewStatus;

    /**
     * 审批值
     */
    private String reviewType;

    /**
     * 审批异常原因
     */
    private String reviewExceptional;

    /**
     * 审核userId
     */
    private Long reviewUserId;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 审核时间
     */
    private Date reviewTime;

    /**
     * 审核驳回原因
     */
    private String reviewRejectReason;

    /**
     * 申请人组编编码
     */
    private String applyOrgCode;

    /**
     * 发件人组编编码
     */
    private String senderOrgCode;

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

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public String getReviewExceptional() {
        return reviewExceptional;
    }

    public void setReviewExceptional(String reviewExceptional) {
        this.reviewExceptional = reviewExceptional;
    }

    public Long getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(Long reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewRejectReason() {
        return reviewRejectReason;
    }

    public void setReviewRejectReason(String reviewRejectReason) {
        this.reviewRejectReason = reviewRejectReason;
    }

    public String getApplyOrgCode() {
        return applyOrgCode;
    }

    public void setApplyOrgCode(String applyOrgCode) {
        this.applyOrgCode = applyOrgCode;
    }

    public String getSenderOrgCode() {
        return senderOrgCode;
    }

    public void setSenderOrgCode(String senderOrgCode) {
        this.senderOrgCode = senderOrgCode;
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
}