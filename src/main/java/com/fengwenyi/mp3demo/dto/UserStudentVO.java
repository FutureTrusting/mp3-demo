package com.fengwenyi.mp3demo.dto;

import lombok.*;

import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author : Caixin
 * @date 2019/5/23 18:45
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStudentVO {

    private String id;
    private String username;

    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.add(0L);
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();
        String string = longAdder.toString();
        System.err.println(string);
    }
}
