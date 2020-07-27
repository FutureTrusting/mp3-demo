package com.fengwenyi.mp3demo.testlambda;

import cn.hutool.core.util.RandomUtil;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 01376494
 * 图片压缩 需要先在目录下创建 最后一个文件夹（例如github-pages）
 */
public class TestLambdaV9 {

    public static void main(String[] args) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] temp = new byte[1024000];
        FileInputStream inputStream = new FileInputStream("G:\\aaa\\aaa.zip");
        int b = 0;
        String fileStr=null;
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        while ((b = inputStream.read(temp)) != -1) {
            System.err.println(b);
            final String  encode= encoder.encode(temp);
            final FileWriter fileWriter = new FileWriter("G:\\aaa\\"+atomicInteger.incrementAndGet()+".txt", true);
            fileWriter.write(encode);
            fileStr+= encoder.encode(temp);
        }
        BASE64Decoder decoder = new BASE64Decoder();
        System.err.println(fileStr);
        byte[] appByte = decoder.decodeBuffer(fileStr);
        ByteBuffer bb = ByteBuffer.wrap(appByte);
        FileChannel fc = new FileOutputStream("ddd.zip").getChannel();
        fc.write(bb);
        fc.close();
    }
}
