package com.fengwenyi.mp3demo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author : Caixin
 * @date 2019/7/1 16:26
 */
public class SimpleExample1 {

    public static void main(String[] args) throws IOException {

//        Gson gson = new GsonBuilder().create();
//        gson.toJson("Hello", System.out);
//        gson.toJson(123, System.out);

        Writer writer = new FileWriter("Output.json");

        Gson gson = new GsonBuilder().create();
        gson.toJson("Hello", writer);
        gson.toJson(123, writer);

        writer.close();

    }
}
