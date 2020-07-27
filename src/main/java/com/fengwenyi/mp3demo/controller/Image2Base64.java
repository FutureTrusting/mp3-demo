package com.fengwenyi.mp3demo.controller;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

/**
 * @author : Caixin
 * @date 2019/10/8 16:55
 */
public class Image2Base64 {

    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            URL url = new URL("http://123.58.36.21:9000/common-service/191011vwEI1zlzwtrZQ2Osb_3PnOp5wviav7aHMX-Ww8_pMe4.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20191011T061919Z&X-Amz-SignedHeaders=host&X-Amz-Expires=86399&X-Amz-Credential=9D2L6WY26NQ9V8BMJ53O%2F20191011%2FCN_NORTH_1%2Fs3%2Faws4_request&X-Amz-Signature=3eed9ec5b432ccb0ce358ac9934fe48d32b8eddc3b22eee27accda12f0ff63ea");
            String urlFile = url.getFile();
            System.out.println(url);
            Optional<String> buildPath = Optional.ofNullable(url.getPath());
            String oriName = buildPath.map(x -> x.substring(x.lastIndexOf(".") + 1)).orElse("");
            byte[] buffer = new byte[1024];
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BASE64Encoder decoder = new BASE64Encoder();

        String str1 = DatatypeConverter.printBase64Binary(outStream.toByteArray());
        System.err.println(str1);
        String base64 = decoder.encode(outStream.toByteArray());
//        System.err.print(base64);
//        System.err.print(str1.equalsIgnoreCase(base64));
    }


}
