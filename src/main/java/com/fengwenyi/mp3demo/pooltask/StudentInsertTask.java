package com.fengwenyi.mp3demo.pooltask;

import com.fengwenyi.mp3demo.dao.StudentDao;
import com.fengwenyi.mp3demo.enums.Type;
import com.fengwenyi.mp3demo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author : Caixin
 * @date 2019/5/27 10:28
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class StudentInsertTask implements Runnable{
    private List<Student> students;
    private StudentDao studentDao;
    private CountDownLatch latch;

    public StudentInsertTask(List<Student> students, StudentDao studentDao, CountDownLatch latch) {
        this.students = students;
        this.studentDao = studentDao;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            //插入用户表
            students.forEach(u -> {
                int insertUserRole = studentDao.insert(u);
                log.warn("插入学生表====>>>>>{}", insertUserRole);
            });
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            latch.countDown();
        }
    }
}
