package com.fengwenyi.mp3demo.mmall.highconcurrency.example.publish;

import com.fengwenyi.mp3demo.mmall.highconcurrency.annoations.NotRecommend;
import com.fengwenyi.mp3demo.mmall.highconcurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape () {
        new InnerClass();
    }

    private class InnerClass {

        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
