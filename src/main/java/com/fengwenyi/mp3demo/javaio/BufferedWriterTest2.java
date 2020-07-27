package com.fengwenyi.mp3demo.javaio;

import java.io.*;

/**
 * @author ECHO
 */
public class BufferedWriterTest2 {

    public static void main(String[] args) throws IOException {
//        readLine():方法的原理：
//        其实缓冲区中的该方法，用的还是与缓冲区关联的流对象的 read 方法。
//        只不过，每一次读到一个字符，先不进行具体操作，先进行临时存储。
//        当读取到回车标记时，将临时容器中存储的数据一次性返回。

        //练习：通过缓冲区的形式，对文本文件进行拷贝。
        BufferedReader bufr = new BufferedReader(new FileReader("a.txt"));
        BufferedWriter bufw = new BufferedWriter(new FileWriter("b.txt"));
        String line = null;
        while ((line = bufr.readLine()) != null) {
            bufw.write(line);
            bufw.newLine();
            bufw.flush();
        }
        bufw.close();
        bufr.close();
    }
}
