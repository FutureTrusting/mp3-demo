package com.fengwenyi.mp3demo.controller;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author : Caixin
 * @date 2019/7/5 17:40
 */
public class TryWithResourceController {

    private static final String FileName = "F:\\Program Files\\BaiduNetdiskDownload\\教程目录及说明.txt";

    public static void main(String[] args){

        try (FileInputStream inputStream = new FileInputStream(FileName)) {
            char c1 = (char) inputStream.read();
            System.out.println("c1=" + c1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
