package com.fengwenyi.mp3demo.config;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created with IDEA
 *
 * @author:WangXiaoShuai Date:2019/7/4
 * Time:9:37
 **/
@JacksonXmlRootElement(localName = "Request")
@Getter
@Setter
public class BspDeliverTmServiceRequest extends BspRequest{

    public BspDeliverTmServiceRequest() {

    }

    public BspDeliverTmServiceRequest(BspConfig config) {
        super(config);
    }

    /**
     * 服务名称
     */
    @JacksonXmlProperty(localName = "service", isAttribute = true)
    private final String service = "DeliverTmService";

    @JacksonXmlElementWrapper(localName = "Body", useWrapping = true)
    @JacksonXmlProperty(localName = "DeliverTmRequest")
    private List<DeliverTm> body;

    @Getter
    @Setter
    public static class DeliverTm extends RequestBody {

        /**
         * 快件产品： 可以为空，为空时查询顺丰支持的所有业务类型。 不为空时以数字代码业务类型，
         * 例如： o 1：表示“” o 2：表示“顺丰特惠” o 5：表示“顺丰次晨” o 6：表示“即日件”
         */
        @JacksonXmlProperty(localName = "business_type", isAttribute = true)
        private String businessType;

        /**
         * 货物总重量，包含子母件，单位千克，精确到小数点后2位，如果提供此值，必须>0。
         */
        @JacksonXmlProperty(localName = "weight", isAttribute = true)
        private Double weight;

        /**
         * 货物的总长，宽，高，以半角逗号分隔，单位CM，精确到小数点后2位，包含子母件。
         */
        @JacksonXmlProperty(localName = "volumn", isAttribute = true)
        private String volumn;

        /**
         * 寄件时间，格式为YYYY-MM-DD HH24:MM:SS，示例2013-12-27 17:54:20。
         */
        @JacksonXmlProperty(localName = "consigned_time", isAttribute = true)
        private String consignedTime;

        /**
         * 1：表示查询含价格的接口 0：表示查询不含价格的接口
         */
        @JacksonXmlProperty(localName = "search_price", isAttribute = true)
        private String searchPrice;

        @JacksonXmlElementWrapper(localName = "SrcAddress", useWrapping = false)
        private List<SrcAddress> srcAddress;

        @JacksonXmlElementWrapper(localName = "DestAddress", useWrapping = false)
        private List<DestAddress> destAddress;

        @Getter
        @Setter
        public static class SrcAddress {
            /**
             * 原寄地所在省份，字段填写要求：必须是标准的省名称称谓 如：广东省 如果是直辖市，请直接传北京、上海等，
             * 与字段city同时存在时忽略字段address 如果字段code与字段address皆为空时为必填。
             */
            @JacksonXmlProperty(localName = "province", isAttribute = true)
            private String province;
            /**
             * 原寄地所在城市，必须是标准的城市称谓 如：深圳市，与字段province同时存在时忽略字段address，如果字段code与字段address皆为空时为必填。
             */
            @JacksonXmlProperty(localName = "city", isAttribute = true)
            private String city;
            /**
             * 原寄地所在县/区，必须是标准的县/区称谓，示例：“福田区”。
             */
            @JacksonXmlProperty(localName = "district", isAttribute = true)
            private String district;
            /**
             * 原寄地详细地址，此详细地址需包含省市信息，以提高地址识别的成功率，示例：“广东省深圳市福田区新洲十一街万基商务大厦10楼”，
             * 字段code为空且字段province或字段city其一为空时为必填。
             */
            @JacksonXmlProperty(localName = "address", isAttribute = true)
            private String address;
            /**
             * 原寄地区域代码，如果填写了此项，则忽略字段address，字段province及字段city。
             * 字段address为空且字段province或字段city其一为空时为必填，示例：020、755。
             */
            @JacksonXmlProperty(localName = "code", isAttribute = true)
            private String code;
        }

        @Getter
        @Setter
        public static class DestAddress {
            /**
             * 目的地所在省份，字段填写要求：必须是标准的省名称称谓 如：广东省 如果是直辖市，请直接传北京、上海等，如果字段code为空时为必填。
             */
            @JacksonXmlProperty(localName = "province", isAttribute = true)
            private String province;
            /**
             * 目的地所在城市，必须是标准的城市称谓 如：深圳市，如果字段code为空时为必填。
             */
            @JacksonXmlProperty(localName = "city", isAttribute = true)
            private String city;
            /**
             * 目的地所在县/区，必须是标准的县/区称谓，示例：“福田区”。
             */
            @JacksonXmlProperty(localName = "district", isAttribute = true)
            private String district;
            /**
             * 目的地详细地址，此详细地址需包含省市信息，以提高地址识别的成功率，示例：“广东省深圳市福田区新洲十一街万基商务大厦10楼”。
             */
            @JacksonXmlProperty(localName = "address", isAttribute = true)
            private String address;
            /**
             * 目的地区域代码，如果填写了此项，则查询时忽略省市区具体地址，如果不填此项，则综合省市区具体地址识别区域代码，
             * 字段province或city为空时为必填，示例：020、755。
             */
            @JacksonXmlProperty(localName = "code", isAttribute = true)
            private String code;
        }

    }

}
