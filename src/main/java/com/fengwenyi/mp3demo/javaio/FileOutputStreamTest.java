package com.fengwenyi.mp3demo.javaio;

import java.io.*;

/**
 * @author ECHO
 */
public class FileOutputStreamTest {
    public static void main(String[] args) throws IOException {
        //  抽象基类： InputStream， OutputStream。
        //  字节流可以操作任何数据。
        //  注意：字符流使用的数组是字符数组。 char [] chs
        //  字节流使用的数组是字节数组。 byte [] bt
        FileOutputStream fos = new FileOutputStream("a.txt");
//        byte[] bytes = new byte[1024];
        fos.write("abcde".getBytes());//直接将数据写入到了目的地。
        fos.close();//只关闭资源。
        FileInputStream fis = new FileInputStream("a.txt");
        //fis.available();//获取关联的文件的字节数。
        //如果文件体积不是很大。
        //可以这样操作。
        byte[] buf = new byte[fis.available()];//创建一个刚刚好的缓冲区。
        //但是这有一个弊端，就是文件过大，大小超出 jvm 的内容空间时，会内存溢出。
        fis.read(buf);
        System.out.println(new String(buf));


//      需求： copy 一个图片。
        BufferedInputStream bufis = new BufferedInputStream(new FileInputStream("1.jpg"));
        BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream("2.jpg"));
        int by = 0;
        while ((by = bufis.read()) != -1) {
            bufos.write(by);
        }
        bufos.close();
        bufis.close();
    }
}
