package com.fengwenyi.mp3demo.config;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created with IDEA
 *
 * @author:WangXiaoShuai Date:2019/7/4
 * Time:9:58
 **/
@Getter
@Setter
@ToString
@JacksonXmlRootElement(localName = "Response")
public class BspDeliverTmServiceResponse extends BspResponse {

    @JacksonXmlProperty(localName = "service", isAttribute = true)
    private String service = "DeliverTmService";

    @JacksonXmlElementWrapper(localName = "Body")
    @JacksonXmlProperty(localName = "DeliverTmResponse")
    private List<DeliverTmResponse> body;

    @Setter
    @Getter
    public static class DeliverTmResponse extends ResponseBody {

        @JacksonXmlProperty(localName = "DeliverTm")
        private List<DeliverTm> deliverTms;

        @Setter
        @Getter
        public static class DeliverTm {
            /**
             * 返回一个数字，代表所支持的快件产品：  1：表示“顺丰次日”  2：表示“顺丰特惠”  5：表示“顺丰次晨”  6：表示“即日件”
             */
            @JacksonXmlProperty(localName = "business_type", isAttribute = true)
            private String businessType;

            /**
             * 业务类型描述：字段business_type对应的描述。
             */
            @JacksonXmlProperty(localName = "business_type_desc", isAttribute = true)
            private String businessTypeDesc;

            /**
             * 预计派送时间或预计派送时间段， 返回时间段时2个时间逗号分隔 时间段格式：YYYY-MM-DD HH24:MM:SS，, YYYY-MM-DD HH24:MM:SS，
             * 示例：2013-12-27 12:00:00,2013-12-27 15:00:00，表示2013年12月27日12点到15点之间到达。
             */
            @JacksonXmlProperty(localName = "deliver_time", isAttribute = true)
            private String deliverTime;

            /**
             * 费用 当请求search_price为0时不返回此节点以适应老版本的时效查询
             */
            @JacksonXmlProperty(localName = "price", isAttribute = true)
            private Double price;
        }

    }

}
