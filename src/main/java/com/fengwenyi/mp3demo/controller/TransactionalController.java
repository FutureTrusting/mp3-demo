package com.fengwenyi.mp3demo.controller;

import com.fengwenyi.mp3demo.response.R;
import com.fengwenyi.mp3demo.transactional.TransactionalIdCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Caixin
 * @date 2019/9/18 22:09
 */

@RestController
@RequestMapping("/test")
public class TransactionalController {

    @Autowired
    TransactionalIdCardService transactionalIdCardService;

    @GetMapping("/transactional")
    public R testTransactional(){
        return R.success();
    }

}
