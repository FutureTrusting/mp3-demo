package com.fengwenyi.mp3demo.javaio;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ECHO
 */
public class FileWriterTest {
    /**
     * 1,将文本数据存储到一个文件中
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("demo.txt");
        fw.write("abcdec");
        fw.flush();
        fw.write("kkkk");
        fw.close();
        //完整的异常处理方式
        FileWriter fw2 = null;
        try {
            fw2 = new FileWriter("F:\\Program Files (x86)\\ECHO\\mp3-demo\\demo.txt");
            fw2.write("x");
            fw2.flush();
            fw2.write("y");
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            if (fw2 != null) {
                try {
                    fw2.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.toString());
                }
            }
        }
        // try with resource
        try (FileWriter fw3 = new FileWriter("demo.txt")) {
            fw3.write("abcdec");
            fw3.flush();
            fw3.write("kkkk");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取一个已有的文本文件， 将文本数据打印出来。
        FileReader fr = new FileReader("demo.txt");
        int ch = 0;
        //一次读一个字符
        while ((ch = fr.read()) != -1) {
           System.err.println((char) ch);
            //a
            //b
            //c
            //d
            //e
            //c
            //k
            //k
            //k
            //k
        }
        fr.close();

        //读一个字符就存入字符数组里，读完 1Kb 再打印。
        FileReader fr2 = null;
        try {
            fr2 = new FileReader("demo.txt");
            //char[] buf = new char[6];//该长度通常都是 1024 的整数倍。
            char[] buf = new char[1024];//该长度通常都是 1024 的整数倍。
            int len = 0;
            while ((len = fr2.read(buf)) != -1) {
                System.out.println(new String(buf, 0, len)); //abcdeckkkk
                //abcdec
                //kkkk
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            if (fr2 != null) {
                try {
                    fr2.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.toString());
                }
            }
        }
    }
}
