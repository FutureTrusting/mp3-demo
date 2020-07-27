package com.fengwenyi.mp3demo.dto;

import java.io.Serializable;

/**
 * us_mail
 * @author 
 */
public class UsMailDo implements Serializable {
    /**
     * 序号
     */
    private Integer id;

    /**
     * 运单号
     */
    private String waybillNo;

    /**
     * 原寄地中转场
     */
    private String sourceTransferCode;

    /**
     * 原寄地城市代码
     */
    private String sourceCityCode;

    /**
     * 原寄地网点代码
     */
    private String sourceDeptCode;

    /**
     * 原寄地单元区域
     */
    private String sourceTeamCode;

    /**
     * 目的地城市代码
     */
    private String destCityCode;

    /**
     * 目的地网点代码
     */
    private String destDeptCode;

    /**
     * 目的地网点代码映射码
     */
    private String destDeptCodeMapping;

    /**
     * 目的地单元区域
     */
    private String destTeamCode;

    /**
     * 目的地中转场
     */
    private String destTransferCode;

    /**
     * 打单时的路由标签信息
     */
    private String destRouteLabel;

    /**
     * 产品名称
     */
    private String proName;

    /**
     * 快件内容：
     */
    private String cargoTypeCode;

    /**
     * 时效代码
     */
    private String limitTypeCode;

    /**
     * 产品类型
     */
    private String expressTypeCode;

    /**
     * 入港
     */
    private String codingMapping;

    /**
     * 出港
     */
    private String codingMappingOut;

    /**
     * XB标志
     */
    private String xbFlag;

    /**
     * 打印标志
     */
    private String printFlag;

    /**
     * 二维码
     */
    private String twoDimensionCode;

    /**
     * 时效类型:
     */
    private String proCode;

    private String printIcon;

    /**
     * 运单号
     */
    private String mailno;

    /**
     * 订单号
     */
    private Long orderNo;

    /**
     * 筛单结果：
     */
    private String filterResult;

    /**
     * 目的地区域代码
     */
    private String destcode;

    /**
     * 原寄地区域代码
     */
    private String origincode;

    /**
     * 运单号（BSP中没有具体表明，根据实际返回值）
     */
    private String rlsErrormsg;

    private String invokeResult;

    private String rlsCode;

    /**
     * 目的地口岸代码
     */
    private String destPortCode;

    /**
     * 目的国别(国别代码如：JP)
     */
    private String destCountry;

    /**
     * 目的地邮编
     */
    private String destPostCode;

    /**
     * 总价值(保留两位小数,数字类型,可补位)
     */
    private String goodsValueTotal;

    /**
     * 币种
     */
    private String currencySymbol;

    /**
     * 件数
     */
    private String goodsNumber;

    /**
     * 国际件图标
     */
    private String proIcon;

    /**
     * 国际件图标
     */
    private String fileIcon;

    /**
     * 国际件图标
     */
    private String fbaIcon;

    /**
     * 国际件图标
     */
    private String icsmIcon;

    /**
     * AB 标
     */
    private String abFlag;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getSourceTransferCode() {
        return sourceTransferCode;
    }

    public void setSourceTransferCode(String sourceTransferCode) {
        this.sourceTransferCode = sourceTransferCode;
    }

    public String getSourceCityCode() {
        return sourceCityCode;
    }

    public void setSourceCityCode(String sourceCityCode) {
        this.sourceCityCode = sourceCityCode;
    }

    public String getSourceDeptCode() {
        return sourceDeptCode;
    }

    public void setSourceDeptCode(String sourceDeptCode) {
        this.sourceDeptCode = sourceDeptCode;
    }

    public String getSourceTeamCode() {
        return sourceTeamCode;
    }

    public void setSourceTeamCode(String sourceTeamCode) {
        this.sourceTeamCode = sourceTeamCode;
    }

    public String getDestCityCode() {
        return destCityCode;
    }

    public void setDestCityCode(String destCityCode) {
        this.destCityCode = destCityCode;
    }

    public String getDestDeptCode() {
        return destDeptCode;
    }

    public void setDestDeptCode(String destDeptCode) {
        this.destDeptCode = destDeptCode;
    }

    public String getDestDeptCodeMapping() {
        return destDeptCodeMapping;
    }

    public void setDestDeptCodeMapping(String destDeptCodeMapping) {
        this.destDeptCodeMapping = destDeptCodeMapping;
    }

    public String getDestTeamCode() {
        return destTeamCode;
    }

    public void setDestTeamCode(String destTeamCode) {
        this.destTeamCode = destTeamCode;
    }

    public String getDestTransferCode() {
        return destTransferCode;
    }

    public void setDestTransferCode(String destTransferCode) {
        this.destTransferCode = destTransferCode;
    }

    public String getDestRouteLabel() {
        return destRouteLabel;
    }

    public void setDestRouteLabel(String destRouteLabel) {
        this.destRouteLabel = destRouteLabel;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getCargoTypeCode() {
        return cargoTypeCode;
    }

    public void setCargoTypeCode(String cargoTypeCode) {
        this.cargoTypeCode = cargoTypeCode;
    }

    public String getLimitTypeCode() {
        return limitTypeCode;
    }

    public void setLimitTypeCode(String limitTypeCode) {
        this.limitTypeCode = limitTypeCode;
    }

    public String getExpressTypeCode() {
        return expressTypeCode;
    }

    public void setExpressTypeCode(String expressTypeCode) {
        this.expressTypeCode = expressTypeCode;
    }

    public String getCodingMapping() {
        return codingMapping;
    }

    public void setCodingMapping(String codingMapping) {
        this.codingMapping = codingMapping;
    }

    public String getCodingMappingOut() {
        return codingMappingOut;
    }

    public void setCodingMappingOut(String codingMappingOut) {
        this.codingMappingOut = codingMappingOut;
    }

    public String getXbFlag() {
        return xbFlag;
    }

    public void setXbFlag(String xbFlag) {
        this.xbFlag = xbFlag;
    }

    public String getPrintFlag() {
        return printFlag;
    }

    public void setPrintFlag(String printFlag) {
        this.printFlag = printFlag;
    }

    public String getTwoDimensionCode() {
        return twoDimensionCode;
    }

    public void setTwoDimensionCode(String twoDimensionCode) {
        this.twoDimensionCode = twoDimensionCode;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getPrintIcon() {
        return printIcon;
    }

    public void setPrintIcon(String printIcon) {
        this.printIcon = printIcon;
    }

    public String getMailno() {
        return mailno;
    }

    public void setMailno(String mailno) {
        this.mailno = mailno;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getFilterResult() {
        return filterResult;
    }

    public void setFilterResult(String filterResult) {
        this.filterResult = filterResult;
    }

    public String getDestcode() {
        return destcode;
    }

    public void setDestcode(String destcode) {
        this.destcode = destcode;
    }

    public String getOrigincode() {
        return origincode;
    }

    public void setOrigincode(String origincode) {
        this.origincode = origincode;
    }

    public String getRlsErrormsg() {
        return rlsErrormsg;
    }

    public void setRlsErrormsg(String rlsErrormsg) {
        this.rlsErrormsg = rlsErrormsg;
    }

    public String getInvokeResult() {
        return invokeResult;
    }

    public void setInvokeResult(String invokeResult) {
        this.invokeResult = invokeResult;
    }

    public String getRlsCode() {
        return rlsCode;
    }

    public void setRlsCode(String rlsCode) {
        this.rlsCode = rlsCode;
    }

    public String getDestPortCode() {
        return destPortCode;
    }

    public void setDestPortCode(String destPortCode) {
        this.destPortCode = destPortCode;
    }

    public String getDestCountry() {
        return destCountry;
    }

    public void setDestCountry(String destCountry) {
        this.destCountry = destCountry;
    }

    public String getDestPostCode() {
        return destPostCode;
    }

    public void setDestPostCode(String destPostCode) {
        this.destPostCode = destPostCode;
    }

    public String getGoodsValueTotal() {
        return goodsValueTotal;
    }

    public void setGoodsValueTotal(String goodsValueTotal) {
        this.goodsValueTotal = goodsValueTotal;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getProIcon() {
        return proIcon;
    }

    public void setProIcon(String proIcon) {
        this.proIcon = proIcon;
    }

    public String getFileIcon() {
        return fileIcon;
    }

    public void setFileIcon(String fileIcon) {
        this.fileIcon = fileIcon;
    }

    public String getFbaIcon() {
        return fbaIcon;
    }

    public void setFbaIcon(String fbaIcon) {
        this.fbaIcon = fbaIcon;
    }

    public String getIcsmIcon() {
        return icsmIcon;
    }

    public void setIcsmIcon(String icsmIcon) {
        this.icsmIcon = icsmIcon;
    }

    public String getAbFlag() {
        return abFlag;
    }

    public void setAbFlag(String abFlag) {
        this.abFlag = abFlag;
    }
}