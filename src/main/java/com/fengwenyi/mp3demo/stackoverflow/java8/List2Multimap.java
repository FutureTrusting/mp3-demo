package com.fengwenyi.mp3demo.stackoverflow.java8;

import cn.hutool.core.collection.CollectionUtil;
import com.fengwenyi.mp3demo.dto.UserVO;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class List2Multimap {

    public static void main(String[] args) {
        Map<Integer, Integer> test = new HashMap<>();
        test.put(1, 1);
        test.put(2, 2);
        test.put(3, null);
        Map<Integer, Integer> adjustedMap = new HashMap<>(test);
        adjustedMap.replaceAll ((k,v) -> v != null ? 2*v : null);
        System.out.println(test.toString());
        System.out.println(adjustedMap.toString());

        List<UserVO> list1 = null;
        Collection<UserVO> userVOS = CollectionUtils.emptyIfNull(list1);
        System.err.println("emptyIfNull"+ userVOS);
        CollectionUtils.emptyIfNull(list1)
                .stream()
                .map(UserVO::getUsername)
                .forEach(System.out::println);

        List<Foo> list = Arrays.asList(
                new Foo("1", Arrays.asList("a", "b")),
                new Foo("2", Arrays.asList("c", "b")),
                new Foo("3", Arrays.asList("d", "e")),
                new Foo("4", Arrays.asList("f", "g")),
                new Foo("5", Arrays.asList("g", "b")),
                new Foo("6", Arrays.asList("a", "h")),
                new Foo("7", Arrays.asList("b", "b")),
                new Foo("8", Arrays.asList("a", "c"))
        );
        Multimap<String, Foo> listMultimap = list.stream().collect(
                ArrayListMultimap::create,
                (builder, value) -> {
//                    System.err.println(value);
                    value.getTags().forEach(tag -> builder.put(tag, value));
//                    System.err.println(builder);
                },
                (builder1, builder2) -> {
//                    System.err.println(builder1);
//                    System.err.println(builder2);
                    builder1.putAll(builder2);
                }
        );
        System.err.println("listMultimap" + listMultimap);
        ImmutableMultimap<Object, Object> build = list.stream().collect(
                ImmutableMultimap::builder,
                (builder, value) -> value.getTags().forEach(tag -> builder.put(tag, value)),
                (builder1, builder2) -> builder1.putAll(builder2.build())
        ).build();
        //Then the collection would look like this:
        Multimap<String, Foo> map = list.stream().collect(toMultimapByKey(Foo::getTags));
        System.err.println("build" + build);
        System.err.println("map" + map);
//        ImmutableCollection<Object> immutableCollection = build.get("a");
//        Collection<Foo> fooCollection = map.get("b");
    }

    //You can also extract these ad-hoc lambdas into a full-fledged collector, something like this:
    public static <T, K> Collector<T, ?, Multimap<K, T>> toMultimapByKey(Function<? super T, ? extends Iterable<? extends K>> keysMapper) {
        return new MultimapCollector<>(keysMapper);
    }

    private static class MultimapCollector<T, K> implements Collector<T, ImmutableMultimap.Builder<K, T>, Multimap<K, T>> {
        private final Function<? super T, ? extends Iterable<? extends K>> keysMapper;

        private MultimapCollector(Function<? super T, ? extends Iterable<? extends K>> keysMapper) {
            this.keysMapper = keysMapper;
        }

        @Override
        public Supplier<ImmutableMultimap.Builder<K, T>> supplier() {
            return ImmutableMultimap::builder;
        }

        @Override
        public BiConsumer<ImmutableMultimap.Builder<K, T>, T> accumulator() {
            return (builder, value) -> keysMapper.apply(value).forEach(k -> builder.put(k, value));
        }

        @Override
        public BinaryOperator<ImmutableMultimap.Builder<K, T>> combiner() {
            return (b1, b2) -> b1.putAll(b2.build());
        }

        @Override
        public Function<ImmutableMultimap.Builder<K, T>, Multimap<K, T>> finisher() {
            return ImmutableMultimap.Builder<K, T>::build;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.emptySet();
        }
    }

    @Data
    @AllArgsConstructor
    public static class Foo {
        private String name;
        private List<String> tags;
    }
}
