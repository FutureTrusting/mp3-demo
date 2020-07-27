package com.fengwenyi.mp3demo.transactional.impl;

import com.fengwenyi.mp3demo.dao.IdcardDao;
import com.fengwenyi.mp3demo.model.Idcard;
import com.fengwenyi.mp3demo.transactional.TransactionalIdCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Caixin
 * @date 2019/9/18 22:11
 */




@Slf4j
@Service
public class TransactionalIdCardServiceImpl implements TransactionalIdCardService {

    @Autowired
    IdcardDao idcardDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addIdCardPageRows(Idcard idcard) {
        Idcard idcard1 = new Idcard();
        idcard1.setCode("336300");
        int insert2= idcardDao.insert(idcard1);
        return insert2>0;
    }
}
