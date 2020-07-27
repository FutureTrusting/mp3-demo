package com.fengwenyi.mp3demo.stackoverflow.java9;

import java.util.Optional;
import java.util.function.Supplier;

public class OptionalOr {

    public static void main(String[] args) {
        Optional<String> optional1 = Optional.of("Mahesh");
        Supplier<Optional<String>> supplierString = () -> Optional.of("Not Present");
//        optional1 = optional1.or( supplierString);
        optional1.ifPresent( x -> System.out.println("Value: " + x));
        optional1 = Optional.empty();
//        optional1 = optional1.or( supplierString);
        optional1.ifPresent( x -> System.out.println("Value: " + x));
    }
}
