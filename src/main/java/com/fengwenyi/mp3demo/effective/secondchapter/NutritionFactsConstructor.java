package com.fengwenyi.mp3demo.effective.secondchapter;

/**
 * @author : Caixin
 * @date 2019/10/14 14:20
 */
public class NutritionFactsConstructor {

    private final int servingSize;          //required
    private final int serving;              //required
    private final int calories;             //optional
    private final int fat;                  //optional
    private final int sodium;               //optional
    private final int carbohydrate;         //optional

    public NutritionFactsConstructor(int servingSize, int serving, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.serving = serving;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }

    public NutritionFactsConstructor(int servingSize, int serving, int calories) {
        this(servingSize, serving, calories, 0);
    }

    public NutritionFactsConstructor(int servingSize, int serving, int calories, int fat) {
        this(servingSize, serving, calories, 0, 0);
    }

    public NutritionFactsConstructor(int servingSize, int serving, int calories, int fat, int sodium) {
        this(servingSize, serving, calories, 0, 0, 0);
    }

    public NutritionFactsConstructor(int servingSize, int serving) {
        this(servingSize, serving, 0);
    }
}
