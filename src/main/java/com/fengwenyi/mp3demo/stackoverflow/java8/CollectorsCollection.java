package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorsCollection {

    public static void main(String[] args) {
        Student student = new Student("A");
        Student student1 = new Student("A");
        Student student2 = new Student("A");
        List<Student> students = Arrays.asList(student, student1, student2);
        List<String> list2 = Lists.newArrayList("B");

        List<String> list = students.stream()
                .map(Student::getId)
                .collect(Collectors.toCollection(() -> list2));
        System.err.println(list);

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> integers2 = Lists.newArrayList(7, 8);
//        List<Integer> integerList = IntStream.rangeClosed(1, 6)
//                .boxed()
//                .collect(Collectors.toCollection(() -> integers2));

        List<Integer> arrayList = integers.stream()
                .collect(Collectors.toCollection(() -> integers2));

//        System.err.println(integerList);
        System.err.println(arrayList);

        List<Student> students1 = students.stream()
                .collect(Collectors.toCollection(Lists::newArrayList));
        System.err.println(new Gson().toJson(students1));

    }
    @Data
    @AllArgsConstructor
    public static class Student{
        private String id;
    }
}
