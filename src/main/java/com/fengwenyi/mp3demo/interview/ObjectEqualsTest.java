package com.fengwenyi.mp3demo.interview;

import com.fengwenyi.mp3demo.dto.Student;
import com.fengwenyi.mp3demo.dto.UserDo;

import java.util.Objects;

/**
 * @author ECHO
 */
public class ObjectEqualsTest {

    public static void main(String[] args) {
        Student stu1 = new Student("小明",18);
        Student stu2 = new Student("小明",18);
        Student stu3 = null;
        //@Data 重写了hashcode和equals 方法，所以equals相等
        System.out.println(stu1.equals(stu2));//false
        System.out.println(Objects.equals(stu3,stu1));//false
        System.out.println(stu1==stu2);//false
        System.out.println(stu1.hashCode());
        System.out.println(stu2.hashCode());
    }


}
