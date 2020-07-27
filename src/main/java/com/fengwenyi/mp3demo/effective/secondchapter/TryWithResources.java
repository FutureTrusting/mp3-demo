package com.fengwenyi.mp3demo.effective.secondchapter;

import java.io.*;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

/**
 * @author : Caixin
 * @date 2019/10/18 10:29
 */
public class TryWithResources {

    //try-with-resource- the best way to close resources!

    static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    //try-with-resources on multiple resources - short and sweet

    static void copy(String src, String dst) throws IOException {
        try (InputStream inputStream = new FileInputStream(src);
             OutputStream outputStream = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = inputStream.read(buf)) >= 0) {
                outputStream.write(buf, 0, n);
            }
        }
    }


    //try-with-resources with a catch clause

    static String firstLineOfFile(String path, String defaultVal) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            return defaultVal;
        }
    }

}
