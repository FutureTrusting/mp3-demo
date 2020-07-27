package com.fengwenyi.mp3demo.interview.proxy.jdk;

import com.fengwenyi.mp3demo.interview.proxy.UserDTO;

import java.lang.reflect.Proxy;

/**
 * @author ECHO
 */
public class Client {

    public static void main(String[] args) {
        UserDTO user = new UserDTO();
        user.setAddress("地址");
        user.setAge(18);
        user.setName("姓1");

        UserService service = new UserServiceImpl();
        UserServiceInterceptor interceptor = new UserServiceInterceptor(service);
        UserService proxyInstance = (UserService) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                interceptor);
        proxyInstance.addUser(user);
        System.err.println("-----------------");
//        System.err.println(proxyInstance.hashCode());
    }
}
