package com.fengwenyi.mp3demo.java8.expression;

import com.fengwenyi.mp3demo.java8.utils.Letter;
import org.springframework.core.convert.converter.Converter;

import java.util.function.Function;

/**
 * @author : Caixin
 * @date 2019/7/12 10:43
 */
public class FunctionCompound {

    public static void main(String[] args) {

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        //数学上会写作 g(f(x)) 或(g o f)(x)
        //andThen 方法会返回一个函数，它先对输入应用一个给定函数，再对输出应用另一个函数  andThen 则意味着 g(f(x))
        Function<Integer, Integer> h = f.andThen(g);
        Integer integer = h.apply(1);
        //这将返回4
        System.out.println("andThen" + integer);


        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> g1 = x -> x * 2;
        //数学上会写作 f(g(x))或 (f o g)(x) 用 compose 的话，它将意味着 f(g(x))
        //compose 先算g1 ，再把结果算f1函数，反向的andThen
        Function<Integer, Integer> h1 = f1.compose(g1);
        int result = h1.apply(1);
        //这将返回3
        System.out.println("compose" + result);


        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> addFooter = Letter::addFooter;

        //创建一个流水线：先加上抬头，然后进行拼写检查，最后加上一个落款
        Function<String, String> transformationPipeline = addHeader
                .andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        System.out.println("transformationPipeline" + transformationPipeline);

        //第二个流水线可能只加抬头、落款，而不做拼写检查
        Function<String, String> addHeader1 = Letter::addHeader;
        Function<String, String> transformationPipeline2
                = addHeader1.andThen(Letter::addFooter);
        System.out.println("transformationPipeline2" + transformationPipeline2);

    }
}
