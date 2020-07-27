package com.fengwenyi.mp3demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @author: zhengfei
 * @param:
 * @date: 2019-07-18 14:47
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalcTimePromiseFreightVO implements Serializable {

    private static final long serialVersionUID = -4020758740886321969L;
    /**
     * 返回一个数字，代表所支持的快件产品：  1：表示“顺丰次日”  2：表示“顺丰特惠”  5：表示“顺丰次晨”  6：表示“即日件”
     */
    private String expressType;

    /**
     * 业务类型描述：字段business_type对应的描述。
     */
    private String expressTypeDesc;

    /**
     * 预计派送时间或预计派送时间段， 返回时间段时2个时间逗号分隔 时间段格式：YYYY-MM-DD HH24:MM:SS，, YYYY-MM-DD HH24:MM:SS，
     * 示例：2013-12-27 12:00:00,2013-12-27 15:00:00，表示2013年12月27日12点到15点之间到达。
     */
    private String timeStandard;

    /**
     * 费用 当请求search_price为0时不返回此节点以适应老版本的时效查询
     */
    private String freight;
}
