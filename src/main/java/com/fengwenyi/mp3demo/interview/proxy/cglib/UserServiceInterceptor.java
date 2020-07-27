package com.fengwenyi.mp3demo.interview.proxy.cglib;

import com.fengwenyi.mp3demo.interview.proxy.UserDTO;
import com.fengwenyi.mp3demo.interview.proxy.jdk.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ECHO
 */
@Slf4j
public class UserServiceInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (args != null && args.length > 0
                && args[0] instanceof UserDTO) {
            UserDTO userDTO = (UserDTO) args[0];
            if(userDTO.getName().trim().length()<=1){
                throw new RuntimeException("用户姓名长度需要大于1！");
            }
        }
        Object invoke = methodProxy.invokeSuper(o, args);
        System.err.println(" 数据库操作成功！");
        return invoke;
    }

    public static void main(String[] args) {
        UserDTO user = new UserDTO();
        user.setAddress("地址");
        user.setAge(18);
        user.setName("姓");

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(com.fengwenyi.mp3demo.interview.proxy.jdk.UserServiceImpl.class);
        enhancer.setCallback(new UserServiceInterceptor());
        com.fengwenyi.mp3demo.interview.proxy.jdk.UserServiceImpl userService = (UserServiceImpl) enhancer.create();
        userService.addUser(user);

        System.err.println("-----------------");
//      System.err.println(proxyInstance.hashCode());
    }
}
