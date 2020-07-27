package com.fengwenyi.mp3demo.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 *
 * @author:WangXiaoShuai Date:2019/7/4
 * Time:10:06
 **/
@Getter
@Setter
@ToString
public class BspDeliverTmResponse {

    private List<DeliverTm> deliverTms;

    @Setter
    @Getter
    public static class DeliverTm {
        /**
         * 返回一个数字，代表所支持的快件产品：  1：表示“顺丰次日”  2：表示“顺丰特惠”  5：表示“顺丰次晨”  6：表示“即日件”
         */
        private String businessType;

        /**
         * 业务类型描述：字段business_type对应的描述。
         */
        private String businessTypeDesc;

        /**
         * 预计派送时间或预计派送时间段， 返回时间段时2个时间逗号分隔 时间段格式：YYYY-MM-DD HH24:MM:SS，, YYYY-MM-DD HH24:MM:SS，
         * 示例：2013-12-27 12:00:00,2013-12-27 15:00:00，表示2013年12月27日12点到15点之间到达。
         */
        private String deliverTime;

        /**
         * 费用 当请求search_price为0时不返回此节点以适应老版本的时效查询
         */
        private Double price;
    }

}
