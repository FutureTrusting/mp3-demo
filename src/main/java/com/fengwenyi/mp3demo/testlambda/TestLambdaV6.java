package com.fengwenyi.mp3demo.testlambda;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.Objects;

import org.apache.commons.io.FilenameUtils;

/**
 * @author 01376494
 * 本地文件目录 png转jpg
 */
public class TestLambdaV6 {
    public static void main(String[] args) {
        //路径   这里写一个路径进去
//        String path = "C:\\Users\\ECHO\\Pictures\\QQplayerPic\\第3章 数据库\\索引";
        String path = "C:\\Users\\ECHO\\Pictures\\github-pages";
//        String path = "C:\\Users\\ECHO\\Pictures\\QQplayerPic\\第5章 Linux";
        //调用方法
        getFiles(path);
//        ImgUtil.convert(FileUtil.file("C:\\Users\\ECHO\\Pictures\\BnS\\截图_190717_000.jpg"),
//                FileUtil.file("C:\\Users\\ECHO\\Pictures\\BnS\\截图_190717_000.png"));
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
                    String path1 = files[i].getPath();
                    String extensionPath = FilenameUtils.removeExtension(path1) + ".jpg";
                    System.err.println(path1);
                    System.err.println(extensionPath);
                    ImgUtil.convert(FileUtil.file(path1), FileUtil.file(extensionPath));
//                  System.err.println("![](https://alwaysfaith.github.io/img/gc/" + files[i].getName() + ")");
                }
            }
        } else {
            System.err.println("文件：" + file.getPath());
        }
    }
}
