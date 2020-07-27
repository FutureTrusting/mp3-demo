package com.fengwenyi.mp3demo.interview.proxy.jdk;

/**
 * @author ECHO
 */
public class StaticTestV2 extends StaticTestV1{

    public StaticTestV2() {
        System.err.println("Q2");
    }

    public static void staticMethod(){
        System.err.println("W2");
    }

    @Override
    public  void methodV1(){
        System.err.println("E2");
    }

    public static void main(String[] args) {
        StaticTestV2 testV2 = new StaticTestV2();
        StaticTestV1.staticMethod();
        StaticTestV2.staticMethod();
        testV2.methodV1();
    }

}
