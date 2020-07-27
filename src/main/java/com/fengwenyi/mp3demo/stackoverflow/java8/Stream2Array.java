package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.fengwenyi.mp3demo.dto.UserVO;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stream2Array {
    public static void main(String[] args) {
        String line="Key, Name";
        String[] strings = Arrays.stream(line.split(","))
                .map(String::trim)
                .toArray(String[]::new);
        List<UserVO> userV2 = Arrays.asList(
                new UserVO("1", "2", "1", "1"),
                new UserVO("2", "3", "2", "2"),
                new UserVO("3", "3", "3", "3")
        );
        // 定义一个计数器
        StopWatch stopWatch = new StopWatch("统一一组任务耗时");
        // 统计任务一耗时
        List<UserVO> vos = IntStream.rangeClosed(0, 999999)
                .parallel()
                .boxed()
                .map(x -> {
                    String s = x.toString();
                    return new UserVO(s, s, s, s);
                }).collect(Collectors.toList());

        UserVO[] vosArr =IntStream.rangeClosed(0, 999999)
                .parallel()
                .boxed()
                .map(x -> {
                    String s = x.toString();
                    return new UserVO(s, s, s, s);
                })
                .toArray(UserVO[]::new);

        stopWatch.start("任务一");
        List<UserVO> vos1 = vos.stream()
                .sorted(Comparator.comparing(UserVO::getId))
                .collect(Collectors.toList());
        stopWatch.stop();

        stopWatch.start("任务二");
        List<UserVO> collect = vos.parallelStream()
                .sorted(Comparator.comparing(UserVO::getId))
                .collect(Collectors.toList());
        stopWatch.stop();

//        stopWatch.start("任务三");
//        Arrays.parallelSort(vosArr, Comparator.comparing(UserVO::getId));
//        stopWatch.stop();

        stopWatch.start("任务四");
        Arrays.sort(vosArr, Comparator.comparing(UserVO::getId));
        stopWatch.stop();
        //  打印出耗时
        //  StopWatch '统一一组任务耗时': running time (millis) = 385
        //  -----------------------------------------
        //  ms     %     Task name
        //  -----------------------------------------
        //  00098  025%  任务一
        //  00178  046%  任务二
        //  00084  022%  任务三
        //  00025  006%  任务四

        //  StopWatch '统一一组任务耗时': running time (millis) = 374
        //  -----------------------------------------
        //  ms     %     Task name
        //  -----------------------------------------
        //  00112  030%  任务一
        //  00194  052%  任务二
        //  00068  018%  任务四
        System.err.println(stopWatch.prettyPrint());
    }
}
