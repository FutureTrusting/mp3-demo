package com.fengwenyi.mp3demo.stackoverflow.java8;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

public class GroupingByTable {
    class Double<A, B> {
        A a;
        B b;

        public Double(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }

    class Triple<A, B, C> extends Double<A, B> {
        C c;

        public Triple(A a, B b, C c) {
            super(a, b);
            this.c = c;
        }
    }

    @Test
    public void shouldGroupToMap() throws Exception {
        List<Triple<String, String, String>> listOfTriples = asList(
                new Triple<>("a-1", "b-1", "c-1"),
                new Triple<>("a-1", "b-2", "c-2"),
                new Triple<>("a-1", "b-3", "c-3"),
                new Triple<>("a-2", "b-4", "c-4"),
                new Triple<>("a-2", "b-5", "c-5"));

        // This code below compiles and executes OK. If I put a   breakpoint
        // in my EDI I can even see the expected Map being created. However
        // if you uncomment the line below and comment the one after it the
        // code will no longer compile.

        // Map<String, List<Double<String, String>>> myMap =
//        Map<Object, List<Double<Object, Object>>> myMap =
//                listOfTriples.stream().collect(groupingBy(t -> t.a,
//                        mapping((Triple t) -> new Double<>(t.b, t.c), Collectors.toList())));
//        assertEquals(2, myMap.size());

        Map<String, List<Double<String, String>>> myMap =
                listOfTriples
                        .stream()
                        .collect(groupingBy(t -> t.a,
                                        mapping((Triple<String, String, String> t) -> new Double<>(t.b, t.c), toList())
                                ));
    }
}
