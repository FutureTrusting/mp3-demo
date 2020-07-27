package com.fengwenyi.mp3demo.service;


import com.fengwenyi.mp3demo.dto.StudentDto;
import com.fengwenyi.mp3demo.model.City;
import com.fengwenyi.mp3demo.model.Student;
import com.fengwenyi.mp3demo.vo.StudentVO;

import java.util.List;

public interface TestService {

    List<Student> getStudentById();

    List<StudentVO> queryAllStudentDataStore();

    List<StudentVO> getStudentList();

    boolean addStudent(List<City> cities, List<Student> students);

    boolean delStudent(String studentId);

    boolean editStudent(StudentDto build);
}










