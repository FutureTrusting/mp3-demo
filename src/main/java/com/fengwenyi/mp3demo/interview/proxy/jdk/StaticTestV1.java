package com.fengwenyi.mp3demo.interview.proxy.jdk;

/**
 * @author ECHO
 */
public class StaticTestV1 {

    public StaticTestV1() {
        System.err.println("Q");
    }

    public static void staticMethod(){
        System.err.println("W");
    }

    public  void methodV1(){
        System.err.println("E");
    }
}
