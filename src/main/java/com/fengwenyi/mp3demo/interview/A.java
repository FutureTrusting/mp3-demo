package com.fengwenyi.mp3demo.interview;

import com.google.common.base.Joiner;

public class A {
    static {
        System.out.print("1");
    }

    public A() {
        System.out.print("2");
    }
    public static void main(String[] args) {
        A ab = new B();
        ab = new B();
        String join = Joiner.on("").skipNulls().join("0-86273-", "86433", "-");
        System.err.println(join);
    }
}
