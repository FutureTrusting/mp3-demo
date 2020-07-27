package com.fengwenyi.mp3demo.testlambda;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * @author 01376494
 * 本地文件目录 png转jpg
 */
public class TestLambdaV11 {

    public static void main(String[] args) throws IOException {
        //路径这里写一个路径进去
        AES aes = new AES(Mode.ECB, Padding.ZeroPadding, "Qq31669943701234".getBytes());
        String path = "G:\\Program Files (x86)\\aaa";
        //加密文件内容
        //           getEncryptFileContent(path,aes);
        //解密文件内容
        //      getDecryptStrFileContent(path,aes);
        // 加密文件夹和文件
        getEncryptFilesRenameFile(path, aes);
        // 解密文件夹和文件
        //      getDecryptFilesRenameFile(path,aes);
    }

    public static void getFilesRenameFileV2(String path, AES aes) throws IOException {
        File file = new File(path);
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                final String name = files[i].getName();
                if (files[i].isDirectory()) {
                    System.err.println("目录：" + name);
                    final String encryptHex = aes.decryptStr(name);
                    final String pathNew = files[i].getParentFile() + "/" + encryptHex;
                    final boolean renameTo = files[i].renameTo(new File(pathNew));
                    System.err.println(pathNew);
                    getFilesRenameFileV2(pathNew, aes);
                } else {
                    //                    final String encryptHex = aes.encryptHex(name);
                    //                    FileUtil.rename(files[i], encryptHex, Boolean.FALSE, Boolean.TRUE);
                }
            }
        } else {
            System.err.println("文件：" + file.getPath());
        }
    }

    /**
     * 解密文件夹和文件名
     *
     * @param path
     * @param aes
     * @throws IOException
     */
    public static void getDecryptFilesRenameFile(String path, AES aes) throws IOException {
        File file = new File(path);
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                final String name = files[i].getName();
                if (files[i].isDirectory()) {
                    System.err.println("目录：" + name);
                    final String encryptHex = aes.decryptStr(name);
                    FileUtil.rename(files[i], encryptHex, Boolean.FALSE, Boolean.TRUE);
                    final String pathNew = files[i].getParentFile() + "/" + encryptHex;
                    System.err.println(pathNew);
                    getDecryptFilesRenameFile(pathNew, aes);
                } else {
                    final String encryptHex = aes.decryptStr(name);
                    FileUtil.rename(files[i], encryptHex, Boolean.FALSE, Boolean.TRUE);
                }
            }
        } else {
            System.err.println("文件：" + file.getPath());
        }
    }

    /**
     * 加密文件夹和文件名
     *
     * @param path
     * @param aes
     * @throws IOException
     */
    public static void getEncryptFilesRenameFile(String path, AES aes) throws IOException {
        File file = new File(path);
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                final String name = files[i].getName();
                if (files[i].isDirectory()) {
                    System.err.println("目录：" + name);
                    final String encryptHex = aes.encryptHex(name);
                    FileUtil.rename(files[i], encryptHex, Boolean.FALSE, Boolean.TRUE);
                    final String pathNew = files[i].getParentFile() + "/" + encryptHex;
                    System.err.println(pathNew);
                    getEncryptFilesRenameFile(pathNew, aes);
                } else {
                    final String encryptHex = aes.encryptHex(name);
                    FileUtil.rename(files[i], encryptHex, Boolean.FALSE, Boolean.TRUE);
                }
            }
        } else {
            System.err.println("文件：" + file.getPath());
        }
    }


    /**
     * 解密文件内容
     */
    public static void getDecryptStrFileContent(String path, AES aes) throws IOException {
        File file = new File(path);
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                if (files[i].isDirectory()) {
                    System.err.println("目录：" + files[i].getName());
                    getDecryptStrFileContent(files[i].getPath(), aes);
                } else {
                    String content = FileUtils.readFileToString(files[i], "utf-8");
                    if (StrUtil.isNotBlank(content)) {
                        final String decryptStr = aes.decryptStr(content);
                        FileUtils.writeStringToFile(files[i], decryptStr, "utf-8", Boolean.FALSE);
                    }
                }
            }
        } else {
            System.err.println("文件：" + file.getPath());
        }
    }

    /**
     * 加密文件内容
     */
    public static void getEncryptFileContent(String path, AES aes) throws IOException {
        File file = new File(path);
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                if (files[i].isDirectory()) {
                    System.err.println("目录：" + files[i].getName());
                    getEncryptFileContent(files[i].getPath(), aes);
                } else {
                    String content = FileUtils.readFileToString(files[i], "utf-8");
                    if (StrUtil.isNotBlank(content)) {
                        final String encryptHex = aes.encryptHex(content);
                        FileUtils.writeStringToFile(files[i], encryptHex, "utf-8", Boolean.FALSE);
                    }
                }
            }
        } else {
            System.err.println("文件：" + file.getPath());
        }
    }
}
