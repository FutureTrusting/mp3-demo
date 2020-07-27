package com.fengwenyi.mp3demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author : Caixin
 * @date 2019/10/30 9:35
 */
public class AtomicReferenceController {
    public static void main(String[] args) throws InterruptedException {
        AtomicReference<Integer> ref = new AtomicReference<>(10000);
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread(new Task(ref), "Thread-" + i);
            list.add(t);
            t.start();
        }
        for (Thread t : list) {
            t.join();
        }
        System.out.println(ref.get());    // 打印2000
    }

}

class Task implements Runnable {
    private AtomicReference<Integer> ref;

    Task(AtomicReference<Integer> ref) {
        this.ref = ref;
    }

    @Override
    public void run() {
        for (; ; ) {    //自旋操作
            Integer integer = ref.get();
            // CAS操作
            if (ref.compareAndSet(integer, integer + 1)) {
                break;
            }
        }
    }

}
