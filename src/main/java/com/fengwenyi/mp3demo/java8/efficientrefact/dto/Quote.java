package com.fengwenyi.mp3demo.java8.efficientrefact.dto;

/**
 * @author : Caixin
 * @date 2019/7/24 11:42
 */
public class Quote {

    private final String shopName;
    private final double price;
    private final Discount.Code discountCode;
    public Quote(String shopName, double price, Discount.Code code) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = code;
    }

    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }

    public String getShopName() { return shopName; }
    public double getPrice() { return price; }
    public Discount.Code getDiscountCode() { return discountCode; }

    //通过传递 shop 对象返回的字符串给静态工厂方法 parse ，你可以得到 Quote 类的一个实例，
    //它包含了 shop 的名称、折扣之前的价格，以及折扣代码。
    //Discount 服务还提供了一个 applyDiscount 方法，它接收一个 Quote 对象，返回一个字符
    //串，表示生成该 Quote 的 shop 中的折扣价格，代码如下所示。
}
