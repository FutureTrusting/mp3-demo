package com.fengwenyi.mp3demo.java8.efficientrefact.service;

import com.fengwenyi.mp3demo.java8.efficientrefact.service.A;

public interface B extends A {
    @Override
    default void hello() {
        System.out.println("Hello from B");
    }
}
