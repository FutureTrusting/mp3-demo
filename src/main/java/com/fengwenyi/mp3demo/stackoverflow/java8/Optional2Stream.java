package com.fengwenyi.mp3demo.stackoverflow.java8;

import com.google.common.collect.Streams;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Optional2Stream {
    public static void main(String[] args) {
        //How do I convert an Optional<T> into a Stream<T>?
        Optional<String> optional = Optional.of("Hello");
        //In Java-9 the missing stream() method is added, so this code works:
        //Stream<String> texts = optional.stream();

        //In Java 8 you can do this:
        Stream<String> texts = optional.map(Stream::of).orElseGet(Stream::empty);

        Stream<String> texts2 = optional.isPresent() ? Stream.of(optional.get()) : Stream.empty();

        List<Personal> personals = Arrays.asList(
                new Personal(Optional.of("A"), Optional.empty()),
                new Personal(Optional.of("B"), Optional.of("2"))
        );

        List<String> strings = personals.stream()
                .map(Personal::getCreditCard)
                .flatMap(x -> x.map(Stream::of)
                        .orElseGet(Stream::empty))
                .collect(Collectors.toList());

        Optional<String> optionalS = personals.stream()
                .map(Personal::getCreditCard)
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .findFirst();

        //I can recommend Guava's Streams.stream(optional) method if you are not on Java 9. A simple example:
//  Streams.stream(Optional.of("Hello"))
    }

   public static <T> Stream<T> streamopt(Optional<T> opt) {
       return opt.map(Stream::of).orElseGet(Stream::empty);
    }

    @Data
    @AllArgsConstructor
    public static class Personal{
        private Optional<String> creditCard;
        private Optional<String> mortgage;
    }
}
