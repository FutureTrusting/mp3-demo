package com.fengwenyi.mp3demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 下订单（含筛选）接口
 */
@Getter
@Setter
public class BspOrderResponse {

    /***
     * 合同号
     */
    private String contractNumber;
    /**
     * 客户订单号
     */
    private String orderNumber;
    /**
     * 顺丰运单号，一个订单只能有一个母单号，如果是子母单的情况，以半角逗号分隔，主单号在第一个位置，如“755123456789,001123456789,002123456789” ，可用于顺丰电子运单标签打印。
     */
    private String mailNumber;
    /**
     * 顺丰签回单服务运单号
     */
    private String returnTrackingNumber;
    /**
     * 原寄地区域代码，可用于顺丰电子运单标签打印。
     */
    private String originCode;
    /**
     * 原寄地区域代码，可用于顺丰电子运单标签打印。
     */
    private String destCode;
    /**
     * 筛单结果：  1：人工确认  2：可收派  3：不可以收派
     */
    private Integer filterResult;
    /**
     * 如果filter_result=3时为必填，不可以收派的原因代码：  1：收方超范围  2：派方超范围  3：其它原因
     */
    private String remark;
    /**
     * 代理单号
     */
    private String agentMailNumber;
    /**
     * 地址映射码
     */
    private String mappingMark;
    /**
     * 二维码URL（用于CX退货操作的URL）
     */
    private String url;
    /**
     * 用于第三方支付运费的 URL
     */
    private String paymentLink;

    /**
     * 运单详细信息
     */
    private List<BspRlsInfo> rlsInfo;

    @Setter
    @Getter
    public static class BspRlsInfo {
        /**
         * 返回调用结果，ERR：调用失败；OK 调用成功
         */
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
        private String rlsCode;
        /**
         * 错误信息
         */
        private String errorDesc;
        /**
         * 返回结果
         */
        private List<BspRlsDetail> rlsDetail;

    }

    @Getter
    @Setter
    public static class BspRlsDetail {
        /**
         * 电子运单号
         */
        private String waybillNumber;
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
         * 目的地城市代码,eg:755
         */
        private String destCityCode;
        /**
         * 目的地网点代码,eg:755AQ
         */
        private String destDeptCode;
        /**
         * 目的地网点代码映射码
         */
        private String destDeptCodeMapping;
        /**
         * 目的地单元区域,eg:001
         */
        private String destTeamCode;
        /**
         * 目的地单元区域映射码
         */
        private String destTeamCodeMapping;
        /**
         * 目的地中转场
         */
        private String destTransferCode;
        /**
         * 打单时的路由标签信息 如果是大网的路由标签，这里的值是目的地网点代码，如果是同城配的路由标签，这里的值是根据同城配的设置映射出来的值，不同的配置结果会不一样，不能根据-符号切分（如：上海同城配，可能是：集散点-目的地网点-接驳点，也有可能是目的地网点代码-集散点-接驳点）
         */
        private String destRouteLabel;
        /**
         * 产品名称 对应RLS:pro_name
         */
        private String proName;
        /**
         * 快件内容： 如：C816、SP601
         */
        private String cargoTypeCode;
        /**
         * 时效代码, 如：T4
         */
        private String limitTypeCode;
        /**
         * 产品类型,如：B1
         */
        private String expressTypeCode;
        /**
         * 入港映射码 eg:S10
         */
        private String codingMapping;
        /**
         * 出港映射码
         */
        private String codingMappingOut;
        /**
         * XB标志 0:不需要打印XB 1:需要打印XB
         */
        private String xbFlag;
        /**
         * 打印标志 返回值总共有9位，每一位只有0和1两种，0表示按丰密运单默认的规则，1表示显示，顺序如下，如111110000表示打印寄方姓名、寄方电话、寄方公司名、寄方地址和重量，收方姓名、收方电话、收方公司名和收方地址按丰密运单默认规则 1：寄方姓名 2：寄方电话 3：寄方公司名 4：寄方地址 5：重量 6：收方姓名 7：收方电话 8：收方公司名 9：收方地址
         */
        private String printFlag;
        /**
         * 二维码 根据规则生成字符串信息,格式为 MMM={'k1':'（目的地中转场代码）','k2':'（目的地原始网点代码）','k3':'（目的地单元区域）','k4':'（附件通过三维码（express_type_code、 limit_type_code、 cargo_type_code）映射时效类型）','k5':'（运单号）'，'k6':'（AB标识）'}
         */
        private String twoDimensionCode;
        /**
         * 时效类型: 值为二维码中的K4
         */
        private String proCode;
        /**
         * 打印图标 根据托寄物判断需要打印的图标(重货,蟹类,生鲜,易碎，Z标) 返回值有8位，每一位只有0和1两种，0表示按运单默认的规则，1表示显示。后面两位默认0备用。 顺序如下：重货,蟹类,生鲜,易碎,医药类,Z标,0,0 如：00000000表示不需要打印重货，蟹类，生鲜，易碎 ,医药,Z标,备用,备用
         */
        private String printIcon;
        /**
         * AB标
         */
        private String abFlag;
        /**
         * 查询出现异常时返回信息。 返回代码： 0 系统异常 1 未找到运单
         */
        private String errMsg;
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
         * 总价值(保留两位小数，数字类型，可补位)
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
        private String fileIcon;
        private String fbaIcon;
        private String icsmIcon;
    }
}
