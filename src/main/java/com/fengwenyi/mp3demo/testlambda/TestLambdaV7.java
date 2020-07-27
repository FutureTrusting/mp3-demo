package com.fengwenyi.mp3demo.testlambda;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author 01376494
 * 图片压缩 需要先在目录下创建 最后一个文件夹（例如github-pages）
 */
public class TestLambdaV7 {

    public static void main(String[] args) {
        //路径   这里写一个路径进去
//        String path = "C:\\Users\\ECHO\\Pictures\\QQplayerPic\\第3章 数据库\\索引";
//        String path = "C:\\Users\\ECHO\\Pictures\\QQplayerPic";
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
                    String name2 = files[i].getName();
                    String baseName = FilenameUtils.getBaseName(path1);
                    String fullPath = FilenameUtils.getFullPath(path1);
                    String separator = FilenameUtils.getPathNoEndSeparator(path1);
                    System.err.println(name2);
                    System.err.println(baseName);
                    System.err.println(fullPath);
                    System.err.println(separator);

                    List<String> toList = Splitter.on("\\")
                            .trimResults() // 将结果中的空格删除
                            .omitEmptyStrings() //移去结果中的空字符串
                            .splitToList(separator);
                    String s = toList.get(toList.size() - 1)+"\\";
                    try {
                        Thumbnails.of(path1)
                                .size(1280,720)
                                .outputQuality(0.25f). // 图片压缩80%质量
                                toFile(fullPath+s+name2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.err.println("文件：" + file.getPath());
        }
    }
}
