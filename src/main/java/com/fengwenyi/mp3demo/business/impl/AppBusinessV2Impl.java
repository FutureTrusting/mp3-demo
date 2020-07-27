package com.fengwenyi.mp3demo.business.impl;

import com.fengwenyi.mp3demo.business.AppBusiness;
import com.fengwenyi.mp3demo.model.City;
import com.fengwenyi.mp3demo.model.Idcard;
import com.fengwenyi.mp3demo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Caixin
 * @date 2019/8/12 14:01
 */

@Service("appBusinessV2Impl")
@Primary
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class AppBusinessV2Impl implements AppBusiness {
    @Override
    public boolean addStudent(Student student, City city, Idcard idcard) {
        return false;
    }

    @Override
    public String testAppService() {
        return "AppBusinessV2Impl";
    }
}
