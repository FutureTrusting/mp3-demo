package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.fengwenyi.mp3demo.dto.UserVO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamMax {

    public static void main(String[] args) {

        List<UserVO> userVOS = Arrays.asList(
                new UserVO("1", "1", "1", "1"),
                new UserVO("2", "2", "2", "2"),
                new UserVO("3", "3", "3", "3")
        );
        UserVO userVO = new UserVO("1", "1", "1", "1");

        StudentDTO studentDTO = new StudentDTO(1, Arrays.asList(new StudentVO(1, Arrays.asList(1))));
        StudentDTO studentDTO2 = new StudentDTO(2, Arrays.asList(new StudentVO(2, Arrays.asList(2))));
        StudentDO rootObj = new StudentDO(Arrays.asList(studentDTO),Arrays.asList(userVO));

        userVOS.forEach(id -> rootObj.getStudent().add(id));

        List<UserVO> userVOList = userVOS.stream()
                .map(id -> new UserVO(id.getId(), id.getUsername(), id.getPassword(), id.getAddress()))
                .collect(Collectors.toCollection(rootObj::getStudent));


        Optional<UserVO> optionalUserVO = userVOS.stream()
                .max(Comparator.comparing(UserVO::getId));

        long count = IntStream.range(0, 10)
                .peek(System.out::println)
                .limit(3)
                .count();
    }

    @Data
    @AllArgsConstructor
    public static class StudentDO{
        private List<StudentDTO> studentA;
        private List<UserVO> student;
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

    @Data
    @AllArgsConstructor
    public static class Student{
        private String count;
    }
}
