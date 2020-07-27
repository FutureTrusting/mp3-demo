package com.fengwenyi.mp3demo.controller;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.fengwenyi.mp3demo.model.PersonVO;
import com.fengwenyi.mp3demo.service.Formula;
import com.fengwenyi.mp3demo.service.PersonFactory;
import io.swagger.annotations.Api;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author : Caixin
 * @date 2019/7/3 10:08
 */

@Api(tags = "Modern Java - A Guide to Java 8")
@RestController
public class LambdaTestController {

    public static void main(String[] args) {

        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // 纳秒
//        long t0 = System.nanoTime();
//        long count = values.stream().sorted().count();
//        System.out.println(count);
//        long t1 = System.nanoTime();
//        // 纳秒转微秒
//        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
//        System.out.println(String.format("顺序流排序耗时: %d ms", millis));

        // 纳秒
        long t0 = System.nanoTime();

        List<String> strings = values.parallelStream().map(String::toLowerCase).collect(Collectors.toList());
        long count = values.parallelStream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        // 纳秒转微秒
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("并行流排序耗时: %d ms", millis));
        // 并行流排序耗时: 472 ms


        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            // 与老版不同的是，putIfAbent() 方法在 put 之前，
            // 会判断 key 是否已经存在，存在则直接返回 value, 否则 put, 再返回 value
            String ifAbsent = map.putIfAbsent(i, "val" + i);
        }





//        Consumer<PersonVO> greeter = (p) -> System.out.println("Hello, " + p.getFirstName());
//        greeter.accept(new PersonVO("Luke", "Skywalker"));


//        Comparator<PersonVO> comparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getLastName());
//
//        PersonVO p1 = new PersonVO("John", "Doe");
//        PersonVO p2 = new PersonVO("Alice", "Wonderland");
//
//        int compare = comparator.compare(p1, p2); // > 0
//        int compare1 = comparator.reversed().compare(p1, p2);// < 0
//
//        System.out.println(compare);
//        System.out.println(compare1);

//        Supplier<PersonVO> personSupplier = PersonVO::new;
//        PersonVO personVO = personSupplier.get();
//        System.out.println(personVO);

//        Function<String, Integer> toInteger = Integer::valueOf;
//        Function<String, String> backToString = toInteger.andThen(String::valueOf);
//        // "123"
//        String apply = backToString.apply("123");


//        PersonFactory<PersonVO> personFactory = PersonVO::new;
//        PersonVO person = personFactory.create("Peter", "Parker");



//        Something something = new Something();
//        Converter<String, String> startsWith = something::startsWith;
//        String convert = startsWith.convert("Java");
//        System.out.println(convert);


//        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
//        Collections.sort(names, (String a, String b) -> {
//            return b.compareTo(a);
//        });
//        Collections.sort(names, (String a, String b) -> b.compareTo(a));
//        names.sort((a, b) -> b.compareTo(a));
//        System.out.println(names);


//        Formula formula = new Formula() {
//            @Override
//            public double calculate(int a) {
//                return sqrt(a * 100);
//            }
//        };
//        // 100.0
//        double calculate = formula.calculate(100);
//        System.out.println(calculate);
//        // 4.0
//        double sqrt = formula.sqrt(16);
//        System.out.println(sqrt);
    }

    @GetMapping("/default")
    public void defaultMethodsInterfaces() {

    }

}
