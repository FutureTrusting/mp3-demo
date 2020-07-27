package com.fengwenyi.mp3demo.stackoverflow.java9;

import java.util.Optional;

public class OptionalIfPresentOrElse {
    public static void main(String[] args) {
        Optional<Integer> optional = Optional.of(1);

//        optional.ifPresentOrElse( x -> System.out.println("Value: " + x),() ->
//                System.out.println("Not Present."));
//
//        optional = Optional.empty();
//        optional.ifPresentOrElse( x -> System.out.println("Value: " + x),() ->
//                System.out.println("Not Present."));
    }
}
