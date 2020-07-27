package com.fengwenyi.mp3demo.dto;

import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * @author 01376494
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsSkuInfoPicDTO implements Serializable {
    /**
     * 多张图片地址
     */
    private List<UsSkuPicDo> skuPics;
    /**
     * 主键
     */
    private Long id;

    /**
     * 所属组织id
     */
    private Long orgId;

    /**
     * 所属组织代码
     */
    private String orgCode;

    /**
     * 商品编码
     */
    private String skuCode;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 价格 单位分
     */
    private Integer price;

    /**
     * 长 单位CM
     */
    private BigDecimal length;

    /**
     * 宽 单位CM
     */
    private BigDecimal width;

    /**
     * 高 单位CM
     */
    private BigDecimal height;

    /**
     * 件数
     */
    private Integer parcelQuantity;

    /**
     * 实际件数
     */
    private Integer realParcelQuantity;

    /**
     * 个性化包装费
     */
    private BigDecimal packCharges;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String desc;

    /**
     * 状态(1可用 ，2删除)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updateBy;
    private static final long serialVersionUID = -2289632440397982636L;



}
