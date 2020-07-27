package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class IterableForEach {

    public static void main(String[] args) {
        int total = 0;
        StudentDTO studentDTO = new StudentDTO(1, Arrays.asList(new StudentVO(1, Arrays.asList(1))));
        StudentDTO studentDTO2 = new StudentDTO(2, Arrays.asList(new StudentVO(2, Arrays.asList(2))));
        StudentDO rootObj = new StudentDO(Arrays.asList(studentDTO,studentDTO2));
        for (StudentDTO obja : rootObj.getStudentA()) {
            for (StudentVO objb : obja.getStudentB()) {
                total += objb.getCount() * obja.getCount();
            }
        }
//        System.err.println(total);

        int sum1 = rootObj.getStudentA()
                .stream()
                .flatMapToInt(x -> x.getStudentB()
                        .stream()
                        .mapToInt(y -> y.getCount() * x.getCount())
                )
                .sum();
//        System.err.println(sum1);

        int sum2 = rootObj.getStudentA()
                .stream()
                .mapToInt(x -> x.getStudentB()
                        .stream()
                        .mapToInt(y -> y.getCount() * x.getCount())
                        .sum()
                )
                .sum();
//        System.err.println(sum2);

        Map<String, String> map = new HashMap<>();
        map.put("ABC", "123");
        AtomicReference<String> test = new AtomicReference<>("helloABC");
        map.forEach((key, value) -> {
            String replaceAll = test.get().replaceAll(key, value);
//            System.err.println(replaceAll);
            test.set(replaceAll);
        });
        String s = test.get();
        System.err.println(s);

        String test1 = map.entrySet()
                .stream()
                .reduce("helloABC",
                        (s1, e) -> s1.replace(e.getKey(), e.getValue()),
                        (s1, s2) -> null);
        System.err.println(test1);

        List<String> strings = map.entrySet()
                .stream()
                .peek(x -> {
                    System.err.println(x.getKey());
                    System.err.println(x.getValue());
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.err.println(new Gson().toJson(strings));

    }

    @Data
    @AllArgsConstructor
    public static class StudentDO{
        private List<StudentDTO> studentA;
    }
    @Data
    @AllArgsConstructor
    public static class StudentDTO{
        private Integer count;
        private List<StudentVO> studentB;
    }
    @Data
    @AllArgsConstructor
    public static class StudentVO{
        private Integer count;
        private List<Integer> studentC;
    }
}
