package com.fengwenyi.mp3demo.interview.proxy.jdk;

import com.fengwenyi.mp3demo.interview.proxy.UserDTO;

/**
 * @author ECHO
 */
public class UserServiceImpl implements UserService{

    @Override
    public void addUser(UserDTO userDTO) {
        System.err.println("用户数据入库成功，数据为："+ userDTO.toString());
    }
}
