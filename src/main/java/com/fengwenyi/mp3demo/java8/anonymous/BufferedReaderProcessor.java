package com.fengwenyi.mp3demo.java8.anonymous;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author : Caixin
 * @date 2019/7/11 15:05
 */

@java.lang.FunctionalInterface
public interface BufferedReaderProcessor {

    String process(BufferedReader b) throws IOException;

}
