package com.fengwenyi.mp3demo.dto;

import lombok.*;

import java.util.List;

/**
 * @author ECHO
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentV2 {
    private String name ;
    private Integer age;
    private List<StudentV2> students;

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
