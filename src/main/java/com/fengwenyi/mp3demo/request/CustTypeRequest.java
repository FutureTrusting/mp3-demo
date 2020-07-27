package com.fengwenyi.mp3demo.request;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.fengwenyi.mp3demo.enums.CustTypeEnum;
import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ECHO
 */
@Data
public class CustTypeRequest implements Serializable {

    private String student;
    private CustTypeEnum custTypeEnum;

    public static void main(String[] args) {
        int randomInt = RandomUtil.randomInt(99999);
        String hHmmss = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + randomInt;
        System.err.println(randomInt);
        System.err.println(Long.valueOf(hHmmss));
    }

}
