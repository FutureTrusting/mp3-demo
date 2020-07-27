package com.fengwenyi.mp3demo.interview.file;

import com.fengwenyi.mp3demo.interview.proxy.UserDTO;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.TreeMap;

/**
 * @author ECHO
 */
public class FilesCopy {

    public static void copyFileByChannel(File source, File des) throws IOException {
        try (FileChannel sourceChannel = new FileInputStream(source)
                .getChannel();
             FileChannel targetChannel = new FileOutputStream(des).getChannel
                     ();) {
            for (long count = sourceChannel.size(); count > 0; ) {
                long transferred = sourceChannel.transferTo(
                        sourceChannel.position(), count, targetChannel);
                sourceChannel.position(sourceChannel.position() + transferred);
                count -= transferred;
            }
        }
    }

    public static void main(String[] args) {
        TreeMap<Object, Object> treeMap = new TreeMap<>();
        UserDTO dto = new UserDTO();
        UserDTO dto2 = new UserDTO();
        Object put = treeMap.put(dto, dto);
        Object put2 = treeMap.put(dto2, dto2);
        System.err.println(treeMap.toString());
    }

    public static void copyFileByStream(File source, File des) throws IOException {
        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(des);) {
            byte[] bufer = new byte[1024];
            int length;
            while ((length = is.read(bufer)) > 0) {
                os.write(bufer, 0, length);
            }
        }
    }
}
