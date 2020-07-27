package com.fengwenyi.mp3demo.java8.anonymous;

/**
 * @author happyGGQ
 */
@FunctionalInterface
public interface TriFunction<T, U, V, R>{

    R apply(T t, U u, V v);

}
