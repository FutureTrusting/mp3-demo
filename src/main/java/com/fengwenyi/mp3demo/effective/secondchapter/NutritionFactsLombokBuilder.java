package com.fengwenyi.mp3demo.effective.secondchapter;


import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NutritionFactsLombokBuilder {
    private final int servingSize;          //required
    private final int serving;              //required
    private final int calories;             //optional
    private final int fat;                  //optional
    private final int sodium;               //optional
    private final int carbohydrate;         //optional

    public static Builder builder() {
        return new Builder();
    }

    private NutritionFactsLombokBuilder(Builder builder) {
        servingSize = builder.servingSize;
        serving = builder.serving;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static class Builder {
        private int servingSize;          //required
        private int serving;              //required
        private int calories;             //optional
        private int fat;                  //optional
        private int sodium;               //optional
        private int carbohydrate;         //optional

        public Builder servingSize(int servingSize) {
            this.servingSize = servingSize;
            return this;
        }
        public Builder serving(int serving) {
            this.serving = serving;
            return this;
        }
        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }
        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }
        public Builder sodium(int sodium) {
            this.sodium = sodium;
            return this;
        }
        public Builder carbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        public NutritionFactsLombokBuilder build() {
            return new NutritionFactsLombokBuilder(this);
        }
    }


    public static void main(String[] args) {
        NutritionFactsLombokBuilder build = NutritionFactsLombokBuilder.builder()
                .servingSize(1)
                .serving(2)
                .calories(3)
                .fat(4)
                .sodium(5)
                .carbohydrate(6)
                .build();
        System.err.println("NutritionFactsLombokBuilder"+new Gson().toJson(build));
    }
}
