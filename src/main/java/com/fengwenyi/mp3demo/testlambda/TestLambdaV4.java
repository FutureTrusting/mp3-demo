package com.fengwenyi.mp3demo.testlambda;

import java.io.File;
import java.util.Objects;

/**
 * @author 01376494
 */
public class TestLambdaV4 {
    public static void main(String[] args) {
        //路径   这里写一个路径进去
//        String path = "C:\\Users\\ECHO\\Pictures\\QQplayerPic\\第3章 数据库\\索引";
        String path = "C:\\Users\\ECHO\\Pictures\\QQplayerPic\\第11章 Java框架-Spring";
//        String path = "C:\\Users\\ECHO\\Pictures\\QQplayerPic\\第5章 Linux";
        //调用方法
        getFiles(path);
    }

    /**
     * 递归获取某路径下的所有文件，文件夹，并输出
     */

    public static void getFiles(String path) {
        File file = new File(path);
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                if (files[i].isDirectory()) {
                    System.err.println("目录：" + files[i].getName());
                    getFiles(files[i].getPath());
                } else {
                    System.err.println("![](https://alwaysfaith.github.io/img/spring/" + files[i].getName() + ")");
                }
            }
        } else {
            System.err.println("文件：" + file.getPath());
        }
    }
}
