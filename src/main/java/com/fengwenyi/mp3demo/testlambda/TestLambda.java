package com.fengwenyi.mp3demo.testlambda;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.google.common.base.Joiner;

import java.util.Date;

/**
 * @author 01376494
 */
public class TestLambda {
    public static void main(String[] args) {

        String dateStr2 = "2020-03-06 23:59:59";
        Date signDate2 = DateUtil.parse(dateStr2);
        System.err.println(signDate2);

        String dateStr = "2020-03-04 23:59:59";
        Date signDate = DateUtil.parse(dateStr);


        boolean after = DateUtil.beginOfDay(DateUtil.offsetDay(signDate, 2)).isAfter(signDate2);
        System.err.println(after);

        String join = Joiner.on(" ").skipNulls().join("吴彦祖", "18979543305");
        System.err.println(join);
    }
}
