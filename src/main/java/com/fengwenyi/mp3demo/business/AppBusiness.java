package com.fengwenyi.mp3demo.business;

import com.fengwenyi.mp3demo.model.City;
import com.fengwenyi.mp3demo.model.Idcard;
import com.fengwenyi.mp3demo.model.Student;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数据库表关联逻辑处理接口
 *
 * @author Wenyi Feng
 * @since 2018-09-01
 */
public interface AppBusiness {

    boolean addStudent(Student student, City city, Idcard idcard);

    String testAppService();

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        List<List<Integer>> partition = Lists.partition(integers, 1000);
        partition.parallelStream().forEach(x->{
            System.out.println(x);
        });
    }

}
