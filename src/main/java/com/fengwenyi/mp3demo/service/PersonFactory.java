package com.fengwenyi.mp3demo.service;

import com.fengwenyi.mp3demo.model.PersonVO;

public interface PersonFactory<P extends PersonVO> {

    P create(String firstName, String lastName);
}
