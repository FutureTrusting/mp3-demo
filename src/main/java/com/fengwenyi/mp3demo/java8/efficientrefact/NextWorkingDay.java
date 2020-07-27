package com.fengwenyi.mp3demo.java8.efficientrefact;

import com.fengwenyi.mp3demo.dto.Inner;
import com.fengwenyi.mp3demo.dto.Nested;
import com.fengwenyi.mp3demo.dto.Outer;
import com.fengwenyi.mp3demo.java8.efficientrefact.dto.Address;
import com.fengwenyi.mp3demo.java8.efficientrefact.dto.Country;
import com.fengwenyi.mp3demo.java8.efficientrefact.dto.User;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Optional;

/**
 * @author : Caixin
 * @date 2019/7/23 19:28
 */
public class NextWorkingDay implements TemporalAdjuster {
    public static void main(String[] args) {

        Outer outer = new Outer();
        String orElse5 = Optional.of(outer)
                .map(Outer::getNested)
                .map(Nested::getInner)
                // 如果不为空，最终输出 foo 的值
                .map(Inner::getFoo)
                .orElse("为什么");
        System.out.println(orElse5);

        User user = new User();
        String orElse = Optional.of(user)
                .map(User::getAddress)
                .map(Address::getCountry)
                .map(Country::getCountryName)
                .orElse("????");
        System.out.println(orElse);
    }


    @Override
    public Temporal adjustInto(Temporal temporal) {
        int week = temporal.get(ChronoField.DAY_OF_WEEK);
        System.out.println(week);

        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1;
               if (dow == DayOfWeek.FRIDAY) {
                   dayToAdd = 3;
               } else if (dow == DayOfWeek.SATURDAY) {
                   dayToAdd = 2;
               }
               return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }
}
