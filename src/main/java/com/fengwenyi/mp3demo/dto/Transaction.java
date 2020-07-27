package com.fengwenyi.mp3demo.dto;

import lombok.Data;

import java.util.Currency;


/**
 * @author : Caixin
 * @date 2019/7/15 13:42
 */

@Data
public class Transaction {

    private final Trader trader;
    private final int year;
    private final int value;
    private final Currency currency;

    public Transaction(Trader trader, int year, int value,Currency currency){
        this.trader = trader;
        this.year = year;
        this.value = value;
        this.currency = currency;
    }
}
