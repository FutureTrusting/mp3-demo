package com.fengwenyi.mp3demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;


/**
 * @Description:
 * @author: zhengfei
 * @param:
 * @date: 2019-01-25 17:18
 */


@Getter
@AllArgsConstructor
public enum BspRlsCodeEnum {

    /**
     * 0000（接口参数异常）
     * 0010（其它异常）
     * 0001（xml 解析异常）
     * 0002（字段校验异常）
     * 0003（票数节点超出最大值，批量请求最大票数为 100 票）
     * 0004（RLS 获取路由标签的必要字段为空）
     * 1000 成功
     */
    INTERFACE_ERROR("0000", "接口参数异常"),
    OTHER_ERROR("0010", "其它异常"),
    XML_ERROR("0001", "xml 解析异常"),
    VALIDATION_ERROR("0002", "字段校验异常"),
    OUT_LIMIT_ERROR("0003", "票数节点超出最大值，批量请求最大票数为 100 票"),
    GET_ROUTE_ERROR("0004", "RLS 获取路由标签的必要字段为空"),
    SUCCESS("1000", "成功");


    public static BspRlsCodeEnum find(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Arrays.stream(BspRlsCodeEnum.values())
                .filter(v -> (v.getCode().equalsIgnoreCase(value)))
                .findFirst()
                .orElse(null);
    }


    private String code;
    private String desc;

    public static BspRlsCodeEnum findByCode(final String code) {
        return Arrays.stream(values()).filter(value -> value.getCode().equals(code)).findFirst().orElse(null);
    }
}
