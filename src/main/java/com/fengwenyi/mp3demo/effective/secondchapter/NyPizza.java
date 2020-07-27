package com.fengwenyi.mp3demo.effective.secondchapter;

import com.google.gson.Gson;

import java.util.Objects;

/**
 * @author : Caixin
 * @date 2019/10/14 16:18
 */
public class NyPizza extends Pizza {

    public enum Size {SMALL, MIDDLE, LARGE}
    private final Size size;

    public static class Builder extends Pizza.Builder<Builder>{
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        public NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public NyPizza(Builder builder) {
        super(builder);
        this.size = builder.size;
    }

    public static void main(String[] args) {
        NyPizza build = new Builder(Size.LARGE)
                .addTopping(Pizza.Topping.HAM)
                .addTopping(Pizza.Topping.MUSHROOM)
                .build();
        System.err.println(new Gson().toJson(build));//{"size":"LARGE","toppings":["HAM","MUSHROOM"]}
    }
}
