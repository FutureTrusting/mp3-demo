package com.fengwenyi.mp3demo.javaio;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author ECHO
 */
public class InputStreamReaderTest {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        FileReader fr = new FileReader("a.txt");//操作 a.txt 的中的数据使用的本系统默认的 GBK。
//        操作 a.txt 中的数据使用的也是本系统默认的 GBK。
        InputStreamReader isr = new InputStreamReader(new FileInputStream("a.txt"));
//        这两句的代码的意义相同。
//        如果 a.txt 中的文件中的字符数据是通过 utf-8 的形式编码。
//        那么在读取时，就必须指定编码表。
//        那么转换流必须使用。
        InputStreamReader isr2 = new InputStreamReader(new FileInputStream("a.txt"), StandardCharsets.UTF_8);
    }
}
