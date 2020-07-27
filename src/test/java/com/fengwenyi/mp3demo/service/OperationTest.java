package com.fengwenyi.mp3demo.service;

import com.fengwenyi.mp3demo.Mp3DemoApplicationTests;
import com.fengwenyi.mp3demo.dao.StudentDao;
import com.fengwenyi.mp3demo.dto.LoginLog;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Caixin
 * @date 2020/7/7 9:12
 */
@Component
public class OperationTest  extends Mp3DemoApplicationTests {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void testOperationV2() throws IOException {
        final File file = new File("G:\\aaa\\vvv.txt");
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
        String s = null;
        while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            result.append(System.lineSeparator()).append(s);
        }
        br.close();
        final String fileStr = result.toString();

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] appByte = decoder.decodeBuffer(fileStr);
        ByteBuffer bb = ByteBuffer.wrap(appByte);
        FileChannel fc = new FileOutputStream("kkk.zip").getChannel();
        fc.write(bb);
        fc.close();
    }


    @Test
    public void testOperationV3() throws IOException {
        final String fileStr = Optional.ofNullable(studentDao.selectStudent())
                .map(Collection::stream)
                .orElse(Stream.empty())
                .filter(Objects::nonNull)
                .map(LoginLog::getLoginHtml)
                .collect(Collectors.joining(""));
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] appByte = decoder.decodeBuffer(fileStr);
        ByteBuffer bb = ByteBuffer.wrap(appByte);
        FileChannel fc = new FileOutputStream("lll.zip").getChannel();
        fc.write(bb);
        fc.close();
    }


    @Test
    public void testOperation() throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] temp = new byte[1024000];
        FileInputStream inputStream = new FileInputStream("G:\\aaa\\aaa.zip");
        int b = 0;
        String fileStr=null;
        final AtomicInteger atomicInteger = new AtomicInteger(100);
        while ((b = inputStream.read(temp)) != -1) {
            System.err.println(b);
            final String encode= encoder.encode(temp);
            final LoginLog loginLog = new LoginLog();
            final int incrementAndGet = atomicInteger.incrementAndGet();

            loginLog.setLoginHtml(encode);
            loginLog.setUserid(incrementAndGet);
            studentDao.insertStudent(loginLog);

            final FileWriter fileWriter = new FileWriter("G:\\aaa\\"+incrementAndGet+".txt", true);
            fileWriter.write(encode);
            fileStr+= encoder.encode(temp);
        }
        BASE64Decoder decoder = new BASE64Decoder();
        System.err.println(fileStr);
        byte[] appByte = decoder.decodeBuffer(fileStr);
        ByteBuffer bb = ByteBuffer.wrap(appByte);
        FileChannel fc = new FileOutputStream("aaa.zip").getChannel();
        fc.write(bb);
        fc.close();
    }

}

