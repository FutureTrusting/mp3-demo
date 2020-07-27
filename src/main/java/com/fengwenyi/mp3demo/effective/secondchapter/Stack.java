package com.fengwenyi.mp3demo.effective.secondchapter;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @author : Caixin
 * @date 2019/10/21 10:31
 */

// Can you spot the ” memory leak”?

public class Stack {

    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
//        Object result = elements[--size];
//        // Eliminate obsolete reference
//        elements[size] =null;
        return elements[--size];
    }

    /**
     * Ensure space for at least one mo element ，roughly
     * doubling the capacity each time the array needs to grow
     */
    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
