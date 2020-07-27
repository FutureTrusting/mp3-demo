package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

public class MapSetsCollectSets {

    public static void main(String[] args) {
        Map<String, Set<String>> courseTeacherMap = Maps.newHashMap();
        Map<String, Set<String>> teacherStudentMap = Maps.newHashMap();
        Set<String> hashSet1 = new HashSet<>();
        hashSet1.add("English");
        hashSet1.add("Chinese");

        Set<String> hashSet2 = new HashSet<>();
        hashSet2.add("Japan");
        hashSet2.add("France");

        Set<String> hashSet3 = new HashSet<>();
        hashSet3.add("peePooDo1");
        hashSet3.add("peePooDo2");

        Set<String> hashSet4 = new HashSet<>();
        hashSet4.add("peePooDo3");
        hashSet4.add("peePooDo4");

        courseTeacherMap.put("English",hashSet3);
        courseTeacherMap.put("Chinese",hashSet3);
        courseTeacherMap.put("Japan",hashSet4);
        courseTeacherMap.put("France",hashSet4);

        teacherStudentMap.put("peePooDo1",hashSet1);
        teacherStudentMap.put("peePooDo2",hashSet1);
        teacherStudentMap.put("peePooDo3",hashSet2);
        teacherStudentMap.put("peePooDo4",hashSet2);

        Set<CourseStudentPair> result =
                courseTeacherMap.entrySet()
                        .stream()
                        .flatMap(e -> e.getValue()
                                .stream()
//                                .peek(System.out::println)
                                .flatMap(t -> teacherStudentMap.getOrDefault(t, emptySet())
                                        .stream()
                                        .map(s -> new CourseStudentPair(e.getKey(), s))))
                        .collect(toSet());
        System.err.println(new Gson().toJson(result));
    }

    @Data
    @AllArgsConstructor
    public static class CourseStudentPair{
        String studentName; // Taken from teacherStudentMap
        String courseName; // Taken from courseTeacherMap
    }
}
