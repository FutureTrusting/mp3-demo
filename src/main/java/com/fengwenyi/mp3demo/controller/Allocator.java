//package com.fengwenyi.mp3demo.controller;
//
//import java.util.List;
//
///**
// * @author : Caixin
// * @date 2019/7/8 15:31
// */
//public class Allocator {
//
//    private List<Object> als;
//
//    // 一次性申请所有资源
//    synchronized boolean apply(
//            Object from, Object to) {
//        // 经典写法
//        while (als.contains(from) || als.contains(to)) {
//            try {
//                wait();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        als.add(from);
//        als.add(to);
//        return false;
//    }
//
//    // 归还资源
//    synchronized void free(
//            Object from, Object to) {
//        als.remove(from);
//        als.remove(to);
//        notifyAll();
//    }
//
//}
