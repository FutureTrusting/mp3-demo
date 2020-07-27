package com.fengwenyi.mp3demo.interview.proxy.cglib;

import com.fengwenyi.mp3demo.interview.proxy.UserDTO;
import com.fengwenyi.mp3demo.interview.proxy.jdk.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author ECHO
 */
public class Client {

    public static void main(String[] args) {
        UserDTO user = new UserDTO();
        user.setAddress("地址");
        user.setAge(18);
        user.setName("姓12");

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new UserServiceInterceptor());
        UserServiceImpl userService = (UserServiceImpl) enhancer.create();
        userService.addUser(user);

        System.err.println("-----------------");
//      System.err.println(proxyInstance.hashCode());
    }
}
