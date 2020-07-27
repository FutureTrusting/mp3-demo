package com.fengwenyi.mp3demo.javaio;

import java.io.*;

/**
 * @author ECHO
 */
public class BufferedWriterTest {

    public static void main(String[] args) throws IOException {
//        缓冲区的出现提高了对流的操作效率。
//        原理：其实就是将数组进行封装。
//        对应的对象：
//    BufferedWriter：
//        特有方法：
//                  newLine()：跨平台的换行符。
//    BufferedReader：
//        特有方法：
//                  readLine():一次读一行，到行标记时，将行标记之前的字符数据作为字符串返回。当读到末尾时，返回 null。
//        在使用缓冲区对象时，要明确，缓冲的存在是为了增强流的功能而存在，
//        所以在建立缓冲区对象时，要先有流对象存在。
//        其实缓冲内部就是在使用流对象的方法，只不过加入了数组对数据进行了临时存储。为
//        了提高操作数据的效率。
//        代码上的体现：
//          写入缓冲区对象。
//          建立缓冲区对象必须把流对象作为参数传递给缓冲区的构造函数。
        BufferedWriter bufw = new BufferedWriter(new FileWriter("buf.txt"));
        bufw.write("abce");//将数据写入到了缓冲区。
        bufw.newLine();
        bufw.write("zxc");
        bufw.flush();//对缓冲区的数据进行刷新。将数据刷到目的地中。
        bufw.close();//关闭缓冲区，其实关闭的是被包装在内部的流对象。
        //读取缓冲区对象。
        BufferedReader bufr = new BufferedReader(new FileReader("buf.txt"));
        String line = null;
        //按照行的形式取出数据。取出的每一个行数据不包含回车符。
        while ((line = bufr.readLine()) != null) {
            System.out.println(line);
            //abce
            //zxc
        }
        bufr.close();
    }
}
