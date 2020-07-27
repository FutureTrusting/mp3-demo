package com.fengwenyi.mp3demo.java8.efficientrefact.service.impl;

import com.fengwenyi.mp3demo.java8.efficientrefact.service.A;
import com.fengwenyi.mp3demo.java8.efficientrefact.service.B;

public class C extends D implements B, A {

    // 依据规则(1)，类中声明的方法具有更高的优先级。
    // D 并未覆盖 hello 方法，可是它实现了接口
    // A 。所以它就拥有了接口 A 的默认方法。规则(2)说如果类或者父类没有对应的方法，那么就应
    // 该选择提供了最具体实现的接口中的方法。因此，编译器会在接口 A 和接口 B 的 hello 方法之间做
    // 选择。由于 B 更加具体，所以程序会再次打印输出“Hello from B”。

    public static void main(String... args) {
        new C().hello();
    }
}
