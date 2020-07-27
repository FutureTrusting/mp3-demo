package com.fengwenyi.mp3demo.util;

import com.fengwenyi.mp3demo.dto.MailNoDTO;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Description:
 * @author: zhengfei
 * @param:
 * @date: 2019-03-20 11:06
 */
public class MailNoUtil {
    private static String TEMP = ",";

    public static MailNoDTO splitMailNo(String mailNo) {
        if (StringUtils.isBlank(mailNo)) {
            return MailNoDTO.builder().mainMailNo("").subMailNo("").build();
        }
        if (!mailNo.contains(TEMP)) {
            return MailNoDTO.builder().mainMailNo(mailNo).subMailNo("").build();
        }
        String[] split = mailNo.split(TEMP);
        if (split.length < 2) {
            return MailNoDTO.builder().mainMailNo(split[0]).subMailNo("").build();
        }
        String mainMailNo = split[0];
        String subMailNo = mailNo.replace(mainMailNo, "");
        if (subMailNo.startsWith(TEMP)) {
            subMailNo = subMailNo.substring(1);
        }
        return MailNoDTO.builder().mainMailNo(mainMailNo).subMailNo(subMailNo).build();
    }

    //version 3
    public static MailNoDTO split(String mailNo) {
        if (StringUtils.isBlank(mailNo)) {
            return null;
        }
        List<String> splitedMailNo = Splitter.on(TEMP)
                .trimResults()
                .omitEmptyStrings()
                .limit(2)
                .splitToList(mailNo);
        int size = splitedMailNo.size();
        return MailNoDTO.builder().mainMailNo(size > 0 ? splitedMailNo.get(0) : null)
                .subMailNo(size > 1 ? splitedMailNo.get(1) : null).build();


    }

    public static void main(String[] args) {

        System.out.println(split("1111q,1111,111111111a").toString());
        String mainMailNo = "1";
        String mailNo = "1,1";


        String a = StringUtils.isBlank(mailNo) ? mainMailNo : (StringUtils.isBlank(mainMailNo) ? mailNo : mainMailNo + "," + mailNo);
        System.out.println(a);

    }


}
