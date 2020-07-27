package com.fengwenyi.mp3demo.dto;

import lombok.*;

import java.util.List;
import java.util.Objects;

/**
 * @author ECHO
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Student {
    private String name ;
    private Integer age;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Student)) return false;
//        Student student = (Student) o;
//        return getName().equals(student.getName()) &&
//                getAge().equals(student.getAge());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getName(), getAge());
//    }
}
