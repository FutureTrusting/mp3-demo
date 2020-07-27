package com.fengwenyi.mp3demo.effective.secondchapter;

/**
 * @author : Caixin
 * @date 2019/10/14 14:29
 */

public class NutritionFactsGetterSetter {

    private  int servingSize;          //required
    private  int serving;              //required
    private  int calories;             //optional
    private  int fat;                  //optional
    private  int sodium;               //optional
    private  int carbohydrate;         //optional


    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public int getServing() {
        return serving;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }
}
