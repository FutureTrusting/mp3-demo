package com.fengwenyi.mp3demo.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * us_order
 * @author
 */
public class UsOrderDo implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 订单号
     */
    private Long orderNo;

    /**
     * 主运单号
     */
    private String mainMailNo;

    /**
     * 寄件人姓名
     */
    private String senderPerson;

    /**
     * 寄件人电话
     */
    private String senderPersonTel;

    /**
     * 寄件人省
     */
    private String senderPersonProvince;

    /**
     * 寄件人市
     */
    private String senderPersonCity;

    /**
     * 寄件人区
     */
    private String senderPersonCounty;

    /**
     * 寄件详细地址
     */
    
    private String senderPersonDetailAddress;

    /**
     * 寄件人详细地址
     */
    
    private String senderPersonAddress;

    /**
     * 寄件区域,86大陆，852香港，853澳门，886台湾
     */
    private String senderRegion;

    /**
     * 寄件公司名称
     */
    private String senderCompany;

    /**
     * 寄件统一编码
     */
    private String senderCompanyCode;

    /**
     * 寄件人门店编码（成本中心）
     */
    private String senderStoreCode;

    /**
     * 寄件人门店类型（成本类型）
     */
    private Integer senderStoreType;

    /**
     * 收件人姓名
     */
    private String recPerson;

    /**
     * 收件人电话
     */
    
    private String recPersonTel;

    /**
     * 收件人省
     */
    private String recPersonProvince;

    /**
     * 收件人市
     */
    private String recPersonCity;

    /**
     * 收件人区
     */
    private String recPersonCounty;

    /**
     * 收件详细地址
     */
    
    private String recPersonDetailAddress;

    /**
     * 收件人详细地址
     */
    
    private String recPersonAddress;

    /**
     * 收件区域,86大陆，852香港，853澳门，886台湾
     */
    private String recRegion;

    /**
     * 收件公司名称
     */
    private String recCompany;

    /**
     * 收件统一编码
     */
    private String recCompanyCode;

    /**
     * 收件组织管理员id（二级组织）
     */
    private Long recTopOrgId;

    /**
     * 收件人组织编号
     */
    private String recOrgNum;

    /**
     * 收件组织id
     */
    private Long recOrgId;

    /**
     * 收件人门店编码（成本中心）
     */
    private String recStoreCode;

    /**
     * 成本类型(数据字典配置)
     */
    private Integer recStoreType;

    /**
     * 是否港澳台等跨境交易件
     */
    private String crossBorderFlag;

    /**
     * 客户订单货物总声明价值,包含子母件,精确到小数点后3位。如果是跨境件,则必填
     */
    private BigDecimal declaredValue;

    /**
     * 如果目的地是中国大陆的,则默认为CNY,否则默认为USD
     */
    private String declaredValueCurrency;

    /**
     * 取件类型（0、一小时内 1、次日九点 2 自己联系取件时间）
     */
    private Integer takePartType;

    /**
     * 取件时间
     */
    private Date takePartTime;

    /**
     * 产品类型（数据字典）
     */
    private Integer productType;

    /**
     * 付款类型（数据字典）
     */
    private Integer paymentMethod;

    /**
     * 件数
     */
    private Integer parcelQuantity;

    /**
     * 保价
     */
    private String incrementService;

    /**
     * 保价金额
     */
    private BigDecimal insureValue;

    /**
     * 运费
     */
    private BigDecimal freight;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 运单创建时间
     */
    private Date createMailTime;

    /**
     * 物品类型
     */
    private String productName;

    /**
     * 备用字段1
     */
    private String remark1;

    /**
     * 备用字段2
     */
    private String remark2;

    /**
     * 备用字段3
     */
    private String remark3;

    /**
     * 自定义传值e1
     */
    private String e1;

    /**
     * 自定义传值e2
     */
    private String e2;

    /**
     * 订单来源1,"后台管理批量下单";2,"小程序下单"；3,"后台管理单条下单"；4,"开放平台"
     */
    private Integer orderSource;

    /**
     * 订单状态（'订单状态 ：0，已导入，1，已下单，2，顺丰已收件 3, 运输中， 4, 派送中 , 5，已签收 6，异常件）
     */
    private Integer orderStatus;

    /**
     * 状态(1可用 ，2删除）
     */
    private Integer status;

    /**
     * 批次号
     */
    private Long banchNo;

    /**
     * 客户的导入批次号
     */
    private String custImportBatchNo;

    /**
     * 下单人所属组织id
     */
    private Long organizationId;

    /**
     * 月结卡号
     */
    
    private String custId;

    /**
     * 客户编码
     */
    private String clientCode;

    /**
     * 效验码
     */
    private String checkWord;

    /**
     * 0:正常订单 1:异地代发订单 2:批量下单 3:调拨订单,4:客户退货
     */
    private Integer orderType;

    /**
     * 申请门店编码
     */
    private String applyStoreCode;

    /**
     * 客户订单号
     */
    private String custOrderNo;

    /**
     * 货物数量
     */
    private Integer cargoCount;

    /**
     * 重量
     */
    private BigDecimal cargoWeight;

    /**
     * 计费重量
     */
    private BigDecimal meterageWeight;

    /**
     * 打印次数
     */
    private Integer printCount;

    /**
     * 异常原因
     */
    private String abnormalReason;

    /**
     * 商品编码
     */
    private String skuCode;

    /**
     * 是否需要签回单 0：不需要 1：需要
     */
    private Integer needReturnTrackingNo;

    /**
     * 签回单
     */
    private String trackingNo;

    /**
     * 0:不需要报价，1：录入为准，2，计算
     */
    private Integer insureType;

    /**
     * 订单所属组织id
     */
    private Long oneOrgId;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 个性化包装费
     */
    private BigDecimal packFee;

    /**
     * 月结卡号业务编码
     */
    private String customBizCode;

    /**
     * 代收货款金额
     */
    private BigDecimal collectionFee;

    /**
     * 代收货款卡号
     */
    private String collectionCardNo;

    /**
     * 成本中心编码
     */
    private String orgCostCode;

    /**
     * 物料名称
     */
    private String material;

    /**
     * 0:内部用户 1:接口下单 2:sso方式下单
     */
    private Integer enterType;

    /**
     * 签回单状态（1，已下单，2，顺丰已收件 3, 运输中， 4, 派送中 , 5，已签收 6，异常件）
     */
    private Integer trackingOrderStatus;

    /**
     * 签回单异常原因
     */
    private String trackingAbnormalReason;

    /**
     * 签收时间
     */
    private Date signTime;

    /**
     * 签回单服务 1：签名 2：盖章 3：登记身份证号 4：收取身份证复印件
     */
    private String trackingItems;

    /**
     * 口令签收增值服务 0:不使用 1:使用
     */
    private Integer passwordSign;

    /**
     * 等通知派送服务 0:不使用 1:使用
     */
    private Integer noticeService;

    /**
     * 收件人备用电话
     */
    
    private String recAlternateTel;

    /**
     * 下单人账号
     */
    private String createdAccount;

    /**
     * 下单人所属组织id_path
     */
    private String organizationPath;

    /**
     * 揽收日期
     */
    private Date pickupDate;

    /**
     * 揽收时间
     */
    private Date pickupTime;

    /**
     * 揽收员工工号
     */
    private String pickupEmployeeNo;

    /**
     * 派送时间
     */
    private Date dispatchTime;

    /**
     * 0：null 1：立即派送 2：预约派送
     */
    private Integer dispatchType;

    /**
     * 派送员工工号
     */
    private String dispatchEmployeeNo;

    /**
     * 寄件组织id
     */
    private Long senderOrgId;

    /**
     * 寄件组织id_path
     */
    private String senderOrgPath;

    /**
     * 取货序号
     */
    private String orderSequence;

    /**
     * 1：bsp单  2：同城单
     */
    private Integer orderClassify;

    /**
     * 运单号
     */
    private String mailno;

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

    public String getMainMailNo() {
        return mainMailNo;
    }

    public void setMainMailNo(String mainMailNo) {
        this.mainMailNo = mainMailNo;
    }

    public String getSenderPerson() {
        return senderPerson;
    }

    public void setSenderPerson(String senderPerson) {
        this.senderPerson = senderPerson;
    }

    public String getSenderPersonTel() {
        return senderPersonTel;
    }

    public void setSenderPersonTel(String senderPersonTel) {
        this.senderPersonTel = senderPersonTel;
    }

    public String getSenderPersonProvince() {
        return senderPersonProvince;
    }

    public void setSenderPersonProvince(String senderPersonProvince) {
        this.senderPersonProvince = senderPersonProvince;
    }

    public String getSenderPersonCity() {
        return senderPersonCity;
    }

    public void setSenderPersonCity(String senderPersonCity) {
        this.senderPersonCity = senderPersonCity;
    }

    public String getSenderPersonCounty() {
        return senderPersonCounty;
    }

    public void setSenderPersonCounty(String senderPersonCounty) {
        this.senderPersonCounty = senderPersonCounty;
    }

    public String getSenderPersonDetailAddress() {
        return senderPersonDetailAddress;
    }

    public void setSenderPersonDetailAddress(String senderPersonDetailAddress) {
        this.senderPersonDetailAddress = senderPersonDetailAddress;
    }

    public String getSenderPersonAddress() {
        return senderPersonAddress;
    }

    public void setSenderPersonAddress(String senderPersonAddress) {
        this.senderPersonAddress = senderPersonAddress;
    }

    public String getSenderRegion() {
        return senderRegion;
    }

    public void setSenderRegion(String senderRegion) {
        this.senderRegion = senderRegion;
    }

    public String getSenderCompany() {
        return senderCompany;
    }

    public void setSenderCompany(String senderCompany) {
        this.senderCompany = senderCompany;
    }

    public String getSenderCompanyCode() {
        return senderCompanyCode;
    }

    public void setSenderCompanyCode(String senderCompanyCode) {
        this.senderCompanyCode = senderCompanyCode;
    }

    public String getSenderStoreCode() {
        return senderStoreCode;
    }

    public void setSenderStoreCode(String senderStoreCode) {
        this.senderStoreCode = senderStoreCode;
    }

    public Integer getSenderStoreType() {
        return senderStoreType;
    }

    public void setSenderStoreType(Integer senderStoreType) {
        this.senderStoreType = senderStoreType;
    }

    public String getRecPerson() {
        return recPerson;
    }

    public void setRecPerson(String recPerson) {
        this.recPerson = recPerson;
    }

    public String getRecPersonTel() {
        return recPersonTel;
    }

    public void setRecPersonTel(String recPersonTel) {
        this.recPersonTel = recPersonTel;
    }

    public String getRecPersonProvince() {
        return recPersonProvince;
    }

    public void setRecPersonProvince(String recPersonProvince) {
        this.recPersonProvince = recPersonProvince;
    }

    public String getRecPersonCity() {
        return recPersonCity;
    }

    public void setRecPersonCity(String recPersonCity) {
        this.recPersonCity = recPersonCity;
    }

    public String getRecPersonCounty() {
        return recPersonCounty;
    }

    public void setRecPersonCounty(String recPersonCounty) {
        this.recPersonCounty = recPersonCounty;
    }

    public String getRecPersonDetailAddress() {
        return recPersonDetailAddress;
    }

    public void setRecPersonDetailAddress(String recPersonDetailAddress) {
        this.recPersonDetailAddress = recPersonDetailAddress;
    }

    public String getRecPersonAddress() {
        return recPersonAddress;
    }

    public void setRecPersonAddress(String recPersonAddress) {
        this.recPersonAddress = recPersonAddress;
    }

    public String getRecRegion() {
        return recRegion;
    }

    public void setRecRegion(String recRegion) {
        this.recRegion = recRegion;
    }

    public String getRecCompany() {
        return recCompany;
    }

    public void setRecCompany(String recCompany) {
        this.recCompany = recCompany;
    }

    public String getRecCompanyCode() {
        return recCompanyCode;
    }

    public void setRecCompanyCode(String recCompanyCode) {
        this.recCompanyCode = recCompanyCode;
    }

    public Long getRecTopOrgId() {
        return recTopOrgId;
    }

    public void setRecTopOrgId(Long recTopOrgId) {
        this.recTopOrgId = recTopOrgId;
    }

    public String getRecOrgNum() {
        return recOrgNum;
    }

    public void setRecOrgNum(String recOrgNum) {
        this.recOrgNum = recOrgNum;
    }

    public Long getRecOrgId() {
        return recOrgId;
    }

    public void setRecOrgId(Long recOrgId) {
        this.recOrgId = recOrgId;
    }

    public String getRecStoreCode() {
        return recStoreCode;
    }

    public void setRecStoreCode(String recStoreCode) {
        this.recStoreCode = recStoreCode;
    }

    public Integer getRecStoreType() {
        return recStoreType;
    }

    public void setRecStoreType(Integer recStoreType) {
        this.recStoreType = recStoreType;
    }

    public String getCrossBorderFlag() {
        return crossBorderFlag;
    }

    public void setCrossBorderFlag(String crossBorderFlag) {
        this.crossBorderFlag = crossBorderFlag;
    }

    public BigDecimal getDeclaredValue() {
        return declaredValue;
    }

    public void setDeclaredValue(BigDecimal declaredValue) {
        this.declaredValue = declaredValue;
    }

    public String getDeclaredValueCurrency() {
        return declaredValueCurrency;
    }

    public void setDeclaredValueCurrency(String declaredValueCurrency) {
        this.declaredValueCurrency = declaredValueCurrency;
    }

    public Integer getTakePartType() {
        return takePartType;
    }

    public void setTakePartType(Integer takePartType) {
        this.takePartType = takePartType;
    }

    public Date getTakePartTime() {
        return takePartTime;
    }

    public void setTakePartTime(Date takePartTime) {
        this.takePartTime = takePartTime;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getParcelQuantity() {
        return parcelQuantity;
    }

    public void setParcelQuantity(Integer parcelQuantity) {
        this.parcelQuantity = parcelQuantity;
    }

    public String getIncrementService() {
        return incrementService;
    }

    public void setIncrementService(String incrementService) {
        this.incrementService = incrementService;
    }

    public BigDecimal getInsureValue() {
        return insureValue;
    }

    public void setInsureValue(BigDecimal insureValue) {
        this.insureValue = insureValue;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateMailTime() {
        return createMailTime;
    }

    public void setCreateMailTime(Date createMailTime) {
        this.createMailTime = createMailTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getE1() {
        return e1;
    }

    public void setE1(String e1) {
        this.e1 = e1;
    }

    public String getE2() {
        return e2;
    }

    public void setE2(String e2) {
        this.e2 = e2;
    }

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getBanchNo() {
        return banchNo;
    }

    public void setBanchNo(Long banchNo) {
        this.banchNo = banchNo;
    }

    public String getCustImportBatchNo() {
        return custImportBatchNo;
    }

    public void setCustImportBatchNo(String custImportBatchNo) {
        this.custImportBatchNo = custImportBatchNo;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getCheckWord() {
        return checkWord;
    }

    public void setCheckWord(String checkWord) {
        this.checkWord = checkWord;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getApplyStoreCode() {
        return applyStoreCode;
    }

    public void setApplyStoreCode(String applyStoreCode) {
        this.applyStoreCode = applyStoreCode;
    }

    public String getCustOrderNo() {
        return custOrderNo;
    }

    public void setCustOrderNo(String custOrderNo) {
        this.custOrderNo = custOrderNo;
    }

    public Integer getCargoCount() {
        return cargoCount;
    }

    public void setCargoCount(Integer cargoCount) {
        this.cargoCount = cargoCount;
    }

    public BigDecimal getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(BigDecimal cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public BigDecimal getMeterageWeight() {
        return meterageWeight;
    }

    public void setMeterageWeight(BigDecimal meterageWeight) {
        this.meterageWeight = meterageWeight;
    }

    public Integer getPrintCount() {
        return printCount;
    }

    public void setPrintCount(Integer printCount) {
        this.printCount = printCount;
    }

    public String getAbnormalReason() {
        return abnormalReason;
    }

    public void setAbnormalReason(String abnormalReason) {
        this.abnormalReason = abnormalReason;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getNeedReturnTrackingNo() {
        return needReturnTrackingNo;
    }

    public void setNeedReturnTrackingNo(Integer needReturnTrackingNo) {
        this.needReturnTrackingNo = needReturnTrackingNo;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public Integer getInsureType() {
        return insureType;
    }

    public void setInsureType(Integer insureType) {
        this.insureType = insureType;
    }

    public Long getOneOrgId() {
        return oneOrgId;
    }

    public void setOneOrgId(Long oneOrgId) {
        this.oneOrgId = oneOrgId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPackFee() {
        return packFee;
    }

    public void setPackFee(BigDecimal packFee) {
        this.packFee = packFee;
    }

    public String getCustomBizCode() {
        return customBizCode;
    }

    public void setCustomBizCode(String customBizCode) {
        this.customBizCode = customBizCode;
    }

    public BigDecimal getCollectionFee() {
        return collectionFee;
    }

    public void setCollectionFee(BigDecimal collectionFee) {
        this.collectionFee = collectionFee;
    }

    public String getCollectionCardNo() {
        return collectionCardNo;
    }

    public void setCollectionCardNo(String collectionCardNo) {
        this.collectionCardNo = collectionCardNo;
    }

    public String getOrgCostCode() {
        return orgCostCode;
    }

    public void setOrgCostCode(String orgCostCode) {
        this.orgCostCode = orgCostCode;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getEnterType() {
        return enterType;
    }

    public void setEnterType(Integer enterType) {
        this.enterType = enterType;
    }

    public Integer getTrackingOrderStatus() {
        return trackingOrderStatus;
    }

    public void setTrackingOrderStatus(Integer trackingOrderStatus) {
        this.trackingOrderStatus = trackingOrderStatus;
    }

    public String getTrackingAbnormalReason() {
        return trackingAbnormalReason;
    }

    public void setTrackingAbnormalReason(String trackingAbnormalReason) {
        this.trackingAbnormalReason = trackingAbnormalReason;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getTrackingItems() {
        return trackingItems;
    }

    public void setTrackingItems(String trackingItems) {
        this.trackingItems = trackingItems;
    }

    public Integer getPasswordSign() {
        return passwordSign;
    }

    public void setPasswordSign(Integer passwordSign) {
        this.passwordSign = passwordSign;
    }

    public Integer getNoticeService() {
        return noticeService;
    }

    public void setNoticeService(Integer noticeService) {
        this.noticeService = noticeService;
    }

    public String getRecAlternateTel() {
        return recAlternateTel;
    }

    public void setRecAlternateTel(String recAlternateTel) {
        this.recAlternateTel = recAlternateTel;
    }

    public String getCreatedAccount() {
        return createdAccount;
    }

    public void setCreatedAccount(String createdAccount) {
        this.createdAccount = createdAccount;
    }

    public String getOrganizationPath() {
        return organizationPath;
    }

    public void setOrganizationPath(String organizationPath) {
        this.organizationPath = organizationPath;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getPickupEmployeeNo() {
        return pickupEmployeeNo;
    }

    public void setPickupEmployeeNo(String pickupEmployeeNo) {
        this.pickupEmployeeNo = pickupEmployeeNo;
    }

    public Date getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Date dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public Integer getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(Integer dispatchType) {
        this.dispatchType = dispatchType;
    }

    public String getDispatchEmployeeNo() {
        return dispatchEmployeeNo;
    }

    public void setDispatchEmployeeNo(String dispatchEmployeeNo) {
        this.dispatchEmployeeNo = dispatchEmployeeNo;
    }

    public Long getSenderOrgId() {
        return senderOrgId;
    }

    public void setSenderOrgId(Long senderOrgId) {
        this.senderOrgId = senderOrgId;
    }

    public String getSenderOrgPath() {
        return senderOrgPath;
    }

    public void setSenderOrgPath(String senderOrgPath) {
        this.senderOrgPath = senderOrgPath;
    }

    public String getOrderSequence() {
        return orderSequence;
    }

    public void setOrderSequence(String orderSequence) {
        this.orderSequence = orderSequence;
    }

    public Integer getOrderClassify() {
        return orderClassify;
    }

    public void setOrderClassify(Integer orderClassify) {
        this.orderClassify = orderClassify;
    }

    public String getMailno() {
        return mailno;
    }

    public void setMailno(String mailno) {
        this.mailno = mailno;
    }
}