package com.fengwenyi.mp3demo.transactional;

import com.fengwenyi.mp3demo.model.Idcard;
import com.fengwenyi.mp3demo.model.Student;

/**
 * @author : Caixin
 * @date 2019/9/18 22:10
 */
public interface TransactionalIdCardService {

    boolean addIdCardPageRows(Idcard idcard);

}
