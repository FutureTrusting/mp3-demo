package com.fengwenyi.mp3demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 01376494
 */
@Data
public class UsSkuPicVO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 类型(1主图 2明细图)
     */
    private Integer type;

    /**
     * 图片url
     */
    private String url;

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

    private static final long serialVersionUID = 1L;
}