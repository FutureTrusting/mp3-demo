package com.fengwenyi.mp3demo.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 下订单（含筛选）接口
 */
@Getter
@Setter
@ToString
@JacksonXmlRootElement(localName = "Response")
public class BspOrderServiceResponse extends BspResponse {
    @JacksonXmlProperty(localName = "service", isAttribute = true)
    private String service = "OrderService";

    @JacksonXmlElementWrapper(localName = "Body")
    @JacksonXmlProperty(localName = "OrderResponse")
    private List<OrderResponse> body;


    @Getter
    @Setter
    public static class OrderResponse extends ResponseBody {
        /**
         * 客户订单号
         */
        @JacksonXmlProperty(localName = "orderid", isAttribute = true)
        private String orderNumber;
        /**
         * 顺丰运单号，一个订单只能有一个母单号，如果是子母单的情况，以半角逗号分隔，主单号在第一个位置，如“755123456789,001123456789,002123456789” ，可用于顺丰电子运单标签打印。
         */
        @JacksonXmlProperty(localName = "mailno", isAttribute = true)
        private String mailNumber;
        /**
         * 顺丰签回单服务运单号
         */
        @JacksonXmlProperty(localName = "return_tracking_no", isAttribute = true)
        private String returnTrackingNumber;
        /**
         * 原寄地区域代码，可用于顺丰电子运单标签打印。
         */
        @JacksonXmlProperty(localName = "origincode", isAttribute = true)
        private String originCode;
        /**
         * 原寄地区域代码，可用于顺丰电子运单标签打印。
         */
        @JacksonXmlProperty(localName = "destcode", isAttribute = true)
        private String destCode;
        /**
         * 筛单结果：  1：人工确认  2：可收派  3：不可以收派
         */
        @JacksonXmlProperty(localName = "filter_result", isAttribute = true)
        private Integer filterResult;
        /**
         * 如果filter_result=3时为必填，不可以收派的原因代码：  1：收方超范围  2：派方超范围  3：其它原因
         */
        @JacksonXmlProperty(localName = "remark", isAttribute = true)
        private String remark;
        /**
         * 代理单号
         */
        @JacksonXmlProperty(localName = "agentMailno", isAttribute = true)
        private String agentMailNumber;
        /**
         * 地址映射码
         */
        @JacksonXmlProperty(localName = "mapping_mark", isAttribute = true)
        private String mappingMark;
        /**
         * 二维码URL（用于CX退货操作的URL）
         */
        @JacksonXmlProperty(localName = "url", isAttribute = true)
        private String url;
        /**
         * 二维码URL（用于CX退货操作的URL）
         */
        @JacksonXmlProperty(localName = "Payment Link", isAttribute = true)
        private String paymentLink;

        /**
         * 运单详细信息
         */
        @JacksonXmlProperty(localName = "rls_info")
        private List<RlsInfo> rlsInfo;


        @Setter
        @Getter
        public static class RlsInfo {
            /**
             * 返回调用结果，ERR：调用失败；OK 调用成功
             */
            @JacksonXmlProperty(localName = "invoke_result", isAttribute = true)
            private String invokeResult;
            /**
             * 0000（接口参数异常）
             * 0010（其它异常）
             * 0001（xml 解析异常）
             * 0002（字段校验异常）
             * 0003（票数节点超出最大值，批量请求最大票数为 100 票）
             * 0004（RLS 获取路由标签的必要字段为空）
             * 1000 成功
             */
            @JacksonXmlProperty(localName = "rls_code", isAttribute = true)
            private String rlsCode;
            /**
             * 错误信息
             */
            @JacksonXmlProperty(localName = "errorDesc", isAttribute = true)
            private String errorDesc;
            /**
             * 返回结果
             */
            @JacksonXmlProperty(localName = "rls_detail")
            private List<RlsDetail> rlsDetail;

        }

        @Getter
        @Setter
        public static class RlsDetail {
            /**
             * 电子运单号
             */
            @JacksonXmlProperty(localName = "waybillNo", isAttribute = true)
            private String waybillNumber;
            /**
             * 原寄地中转场
             */
            @JacksonXmlProperty(localName = "sourceTransferCode", isAttribute = true)
            private String sourceTransferCode;
            /**
             * 原寄地城市代码
             */
            @JacksonXmlProperty(localName = "sourceCityCode", isAttribute = true)
            private String sourceCityCode;
            /**
             * 原寄地网点代码
             */
            @JacksonXmlProperty(localName = "sourceDeptCode", isAttribute = true)
            private String sourceDeptCode;
            /**
             * 原寄地单元区域
             */
            @JacksonXmlProperty(localName = "sourceTeamCode", isAttribute = true)
            private String sourceTeamCode;
            /**
             * 目的地城市代码,eg:755
             */
            @JacksonXmlProperty(localName = "destCityCode", isAttribute = true)
            private String destCityCode;
            /**
             * 目的地网点代码,eg:755AQ
             */
            @JacksonXmlProperty(localName = "destDeptCode", isAttribute = true)
            private String destDeptCode;
            /**
             * 目的地网点代码映射码
             */
            @JacksonXmlProperty(localName = "destDeptCodeMapping", isAttribute = true)
            private String destDeptCodeMapping;
            /**
             * 目的地单元区域,eg:001
             */
            @JacksonXmlProperty(localName = "destTeamCode", isAttribute = true)
            private String destTeamCode;
            /**
             * 目的地单元区域映射码
             */
            @JacksonXmlProperty(localName = "destTeamCodeMapping", isAttribute = true)
            private String destTeamCodeMapping;
            /**
             * 目的地中转场
             */
            @JacksonXmlProperty(localName = "destTransferCode", isAttribute = true)
            private String destTransferCode;
            /**
             * 打单时的路由标签信息 如果是大网的路由标签，这里的值是目的地网点代码，如果是同城配的路由标签，这里的值是根据同城配的设置映射出来的值，不同的配置结果会不一样，不能根据-符号切分（如：上海同城配，可能是：集散点-目的地网点-接驳点，也有可能是目的地网点代码-集散点-接驳点）
             */
            @JacksonXmlProperty(localName = "destRouteLabel", isAttribute = true)
            private String destRouteLabel;
            /**
             * 产品名称 对应RLS:pro_name
             */
            @JacksonXmlProperty(localName = "proName", isAttribute = true)
            private String proName;
            /**
             * 快件内容： 如：C816、SP601
             */
            @JacksonXmlProperty(localName = "cargoTypeCode", isAttribute = true)
            private String cargoTypeCode;
            /**
             * 时效代码, 如：T4
             */
            @JacksonXmlProperty(localName = "limitTypeCode", isAttribute = true)
            private String limitTypeCode;
            /**
             * 产品类型,如：B1
             */
            @JacksonXmlProperty(localName = "expressTypeCode", isAttribute = true)
            private String expressTypeCode;
            /**
             * 入港映射码 eg:S10
             */
            @JacksonXmlProperty(localName = "codingMapping", isAttribute = true)
            private String codingMapping;
            /**
             * 出港映射码
             */
            @JacksonXmlProperty(localName = "codingMappingOut", isAttribute = true)
            private String codingMappingOut;
            /**
             * XB标志 0:不需要打印XB 1:需要打印XB
             */
            @JacksonXmlProperty(localName = "xbFlag", isAttribute = true)
            private String xbFlag;
            /**
             * 打印标志 返回值总共有9位，每一位只有0和1两种，0表示按丰密运单默认的规则，1表示显示，顺序如下，如111110000表示打印寄方姓名、寄方电话、寄方公司名、寄方地址和重量，收方姓名、收方电话、收方公司名和收方地址按丰密运单默认规则 1：寄方姓名 2：寄方电话 3：寄方公司名 4：寄方地址 5：重量 6：收方姓名 7：收方电话 8：收方公司名 9：收方地址
             */
            @JacksonXmlProperty(localName = "printFlag", isAttribute = true)
            private String printFlag;
            /**
             * 二维码 根据规则生成字符串信息,格式为 MMM={'k1':'（目的地中转场代码）','k2':'（目的地原始网点代码）','k3':'（目的地单元区域）','k4':'（附件通过三维码（express_type_code、 limit_type_code、 cargo_type_code）映射时效类型）','k5':'（运单号）'，'k6':'（AB标识）'}
             */
            @JacksonXmlProperty(localName = "twoDimensionCode", isAttribute = true)
            private String twoDimensionCode;
            /**
             * 时效类型: 值为二维码中的K4
             */
            @JacksonXmlProperty(localName = "proCode", isAttribute = true)
            private String proCode;
            /**
             * 打印图标 根据托寄物判断需要打印的图标(重货,蟹类,生鲜,易碎，Z标) 返回值有8位，每一位只有0和1两种，0表示按运单默认的规则，1表示显示。后面两位默认0备用。 顺序如下：重货,蟹类,生鲜,易碎,医药类,Z标,0,0 如：00000000表示不需要打印重货，蟹类，生鲜，易碎 ,医药,Z标,备用,备用
             */
            @JacksonXmlProperty(localName = "printIcon", isAttribute = true)
            private String printIcon;
            /**
             * AB标
             */
            @JacksonXmlProperty(localName = "abFlag", isAttribute = true)
            private String abFlag;
            /**
             * 查询出现异常时返回信息。 返回代码： 0 系统异常 1 未找到运单
             */
            @JacksonXmlProperty(localName = "errMsg", isAttribute = true)
            private String errMsg;
            /**
             * 目的地口岸代码
             */
            @JacksonXmlProperty(localName = "destPortCode", isAttribute = true)
            private String destPortCode;
            /**
             * 目的国别(国别代码如：JP)
             */
            @JacksonXmlProperty(localName = "destCountry", isAttribute = true)
            private String destCountry;
            /**
             * 目的地邮编
             */
            @JacksonXmlProperty(localName = "destPostCode", isAttribute = true)
            private String destPostCode;
            /**
             * 总价值(保留两位小数，数字类型，可补位)
             */
            @JacksonXmlProperty(localName = "goodsValueTotal", isAttribute = true)
            private String goodsValueTotal;
            /**
             * 币种
             */
            @JacksonXmlProperty(localName = "currencySymbol", isAttribute = true)
            private String currencySymbol;
            /**
             * 件数
             */
            @JacksonXmlProperty(localName = "goodsNumber", isAttribute = true)
            private String goodsNumber;
        }
    }
}
