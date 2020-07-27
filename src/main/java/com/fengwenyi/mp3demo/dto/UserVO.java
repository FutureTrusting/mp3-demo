package com.fengwenyi.mp3demo.dto;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

/**
 * @author : Caixin
 * @date 2019/7/25 15:54
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private String id;
    private String username;
    private String password;
    private String address;

    public static void main(String[] args) {
        String strOne = "2";
        Stream<UserSorted> sortedStream = LongStream.rangeClosed(0, 999999)
                .boxed()
                .map(x -> new UserSorted(x, "UserSorted" + x, DateUtil.offsetMillisecond(new Date(), Math.toIntExact(x))));

        if ("1".equalsIgnoreCase(strOne)) {
            List<UserSorted> sorteds = sortedStream.sorted(comparing(UserSorted::getCreateTime))
                    .sorted(comparing(UserSorted::getId))
                    .collect(Collectors.toList());
            sorteds.forEach(System.err::println);
        } else {
            List<UserSorted> sorted2 = sortedStream.sorted(comparing(UserSorted::getCreateTime).reversed())
                    .sorted(comparing(UserSorted::getId).reversed())
                    .collect(Collectors.toList());
            sorted2.forEach(System.err::println);
        }
    }
}
