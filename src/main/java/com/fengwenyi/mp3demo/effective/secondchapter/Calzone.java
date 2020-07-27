package com.fengwenyi.mp3demo.effective.secondchapter;

import com.google.gson.Gson;

/**
 * @author : Caixin
 * @date 2019/10/14 16:32
 */
public class Calzone extends Pizza {
    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside = false; //Default

        public Builder sauceInside() {
            sauceInside = true;
            return this;
        }

        @Override
        public Calzone build() {
            return new Calzone(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public Calzone(Builder builder) {
        super(builder);
        this.sauceInside = builder.sauceInside;
    }

    public static void main(String[] args) {
        Calzone build = new Builder()
                .sauceInside()
                .addTopping(Topping.SAUSAGE)
                .build();
        System.err.println(new Gson().toJson(build));//{"sauceInside":true,"toppings":["SAUSAGE"]}
    }
}
