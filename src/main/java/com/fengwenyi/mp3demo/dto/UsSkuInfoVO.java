package com.fengwenyi.mp3demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 01376494
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsSkuInfoVO implements Serializable {
    private static final long serialVersionUID = -2022643913767669860L;
    /**
     * 多张图片地址
     */
    private List<UsSkuPicVO> skuPics;
    /**
     * 主键
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 所属组织id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Integer price;

    /**
     * 长 单位CM
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal length;

    /**
     * 宽 单位CM
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal width;

    /**
     * 高 单位CM
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal height;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String desc;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
