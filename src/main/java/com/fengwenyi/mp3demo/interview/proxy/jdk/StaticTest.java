package com.fengwenyi.mp3demo.interview.proxy.jdk;

/**
 * @author ECHO
 */
public class StaticTest {
    public static void main(String[] args) {
        try {
            System.err.println("A");
            int i=10/0;
            System.err.println("B");
        } catch (Exception e) {
            System.err.println("C");
        } finally {
            System.err.println("D");
        }

    }
}
