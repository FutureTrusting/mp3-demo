package com.fengwenyi.mp3demo.service;

import com.fengwenyi.mp3demo.Mp3DemoApplicationTests;
import com.fengwenyi.mp3demo.leetcode.Foo;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : Caixin
 * @date 2019/7/26 9:44
 */
@Component
public class JasyptServiceTest extends Mp3DemoApplicationTests {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void testJasypt() {
        //Qv0RN4wqA2DOqYHMPET09A==
        //Qc3CkmT/k1zJmWihcrP2fg==
        System.out.println(stringEncryptor.encrypt("root"));
    }

    @Test
    public void testCompletableFuture(){
        Foo foo = new Foo();
        foo.one();
        foo.two();
        foo.three();
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            Foo foo = new Foo();
//            foo.one();
//        }).thenRun(() -> {
//            Foo foo = new Foo();
//            foo.two();
//        }).thenRun(() -> {
//            Foo foo = new Foo();
//            foo.three();
//        });
    }
}
