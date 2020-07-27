package com.fengwenyi.mp3demo.interview.proxy.jdk;

import com.fengwenyi.mp3demo.interview.proxy.UserDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ECHO
 */
@Slf4j
@Getter
@Setter
public class UserServiceInterceptor implements InvocationHandler {

    private Object realObj;

    public UserServiceInterceptor(Object realObj) {
        super();
        this.realObj = realObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (args != null && args.length > 0
                && args[0] instanceof UserDTO) {
            UserDTO userDTO = (UserDTO) args[0];
            if(userDTO.getName().trim().length()<=1){
                throw new RuntimeException("用户姓名长度需要大于1！");
            }
        }
        Object invoke = method.invoke(realObj, args);
        System.err.println(" 数据库操作成功！");
        return invoke;
    }

    public static void main(String[] args) {
        UserDTO user = new UserDTO();
        user.setAddress("地址");
        user.setAge(18);
        user.setName("姓");

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
