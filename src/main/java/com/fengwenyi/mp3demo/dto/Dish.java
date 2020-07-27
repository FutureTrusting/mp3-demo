package com.fengwenyi.mp3demo.dto;

import com.fengwenyi.mp3demo.enums.CaloricLevel;
import lombok.Data;

import java.util.Arrays;

/**
 * @author : Caixin
 * @date 2019/7/12 15:09
 */

@Data
public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", vegetarian=" + vegetarian +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }

    public CaloricLevel getCaloricLevel(){
        if (this.getCalories() <= 400) {
            return CaloricLevel.DIET;
        } else if (this.getCalories() <= 700) {
            return CaloricLevel.NORMAL;
        } else {
            return CaloricLevel.FAT;
        }
    }


    //    public enum Type { MEAT, FISH, OTHER }

    public enum Type {
        /**
         * FISH
         */
        FISH("1", "FISH"),
        /**
         * OTHER
         */
        OTHER("2", "OTHER"),
        /**
         * MEAT
         */
        MEAT("3", "MEAT");

        private String code;
        private String desc;

        Type(String code, String desc) {
            this.code = code;
            this.code = desc;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static Type findByCode(final String code) {
            return Arrays.stream(values()).filter(value -> value.getCode().equals(code)).findFirst().orElse(null);
        }

        public static Type findByDesc(final String desc) {
            return Arrays.stream(values()).filter(value -> value.getDesc().equals(desc)).findFirst().orElse(null);
        }
    }

}
