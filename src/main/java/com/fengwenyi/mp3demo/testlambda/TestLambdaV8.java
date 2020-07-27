package com.fengwenyi.mp3demo.testlambda;

import cn.hutool.core.util.RandomUtil;
import com.fengwenyi.mp3demo.dto.Student;
import com.fengwenyi.mp3demo.stackoverflow.java8.CollectorsCollection;
import com.google.common.base.Splitter;
import com.google.gson.Gson;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

/**
 * @author 01376494
 * 图片压缩 需要先在目录下创建 最后一个文件夹（例如github-pages）
 */
public class TestLambdaV8 {

    public static void main(String[] args) {
//        Student student = new Student("1",18);
//        int i = student.getAge() * (-1);
//        System.err.println(i);
        int randomInt = RandomUtil.randomInt(10000, 99999);
        System.err.println(randomInt);
        int randomInt2 = RandomUtil.randomInt(99999);
        System.err.println(randomInt2);

//        try {
//            IntStream.range(0,1000)
//                    .boxed()
//                    .forEach(System.out::println);
//            throw new RuntimeException("xxxxx");
//        } catch (Exception e) {
//            throw new RuntimeException("yyyyyyyyyy");
//        } finally {
//            System.err.println("打发斯蒂芬1");
//            System.err.println("打发斯蒂芬2");
//            System.err.println("打发斯蒂芬3");
//            System.err.println("打发斯蒂芬4");
//        }
    }
}
