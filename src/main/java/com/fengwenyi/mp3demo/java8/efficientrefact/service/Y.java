package com.fengwenyi.mp3demo.java8.efficientrefact.service;

import com.fengwenyi.mp3demo.java8.efficientrefact.service.A;

public interface Y extends A {

    //这个新添加到 Y 接口中的抽象方法 hello 比由接口 A 继承而来的 hello 方法拥有更高的优先级，
    @Override
    void hello();
}
