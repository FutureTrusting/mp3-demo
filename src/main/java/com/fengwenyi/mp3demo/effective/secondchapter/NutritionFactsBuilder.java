package com.fengwenyi.mp3demo.effective.secondchapter;

import com.google.gson.Gson;

/**
 * @author : Caixin
 * @date 2019/10/14 14:28
 */
public class NutritionFactsBuilder {
    private final int servingSize;          //required
    private final int serving;              //required
    private final int calories;             //optional
    private final int fat;                  //optional
    private final int sodium;               //optional
    private final int carbohydrate;         //optional

    public static class Builder {
        //required parameters
        private final int servingSize;          //required
        private final int serving;              //required

        //II Optional parameters - initialized to default values
        private int calories;             //optional
        private int fat;                  //optional
        private int sodium;               //optional
        private int carbohydrate;         //optional

        public Builder(int servingSize, int serving) {
            this.servingSize = servingSize;
            this.serving = serving;
        }

        public Builder calories(int val) {
            this.calories = val;
            return this;
        }

        public Builder fat(int val) {
            this.fat = val;
            return this;
        }

        public Builder sodium(int val) {
            this.sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            this.carbohydrate = val;
            return this;
        }

        public NutritionFactsBuilder builder() {
            return new NutritionFactsBuilder(this);
        }
    }

    private NutritionFactsBuilder(Builder builder) {
        servingSize = builder.servingSize;                  //required
        serving = builder.serving;                          //required
        calories = builder.calories;                        //optional
        fat = builder.fat;                                  //optional
        sodium = builder.sodium;                            //optional
        carbohydrate = builder.carbohydrate;                //optional
    }


    public static void main(String[] args) {
        NutritionFactsBuilder builder = new Builder(1, 2)
                .calories(3)
                .carbohydrate(4)
                .builder();
        System.err.println("new Gson().toJson(builder)"+new Gson().toJson(builder));
    }
}
