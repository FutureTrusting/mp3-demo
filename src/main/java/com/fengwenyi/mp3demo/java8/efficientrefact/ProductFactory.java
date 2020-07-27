package com.fengwenyi.mp3demo.java8.efficientrefact;

import com.fengwenyi.mp3demo.dto.Bond;
import com.fengwenyi.mp3demo.dto.Loan;
import com.fengwenyi.mp3demo.dto.Product;
import com.fengwenyi.mp3demo.dto.Stock;

/**
 * @author : Caixin
 * @date 2019/7/22 15:57
 */
public class ProductFactory {
    //  比如，我们假定你为
    //  一家银行工作，他们需要一种方式创建不同的金融产品：贷款、期权、股票，等等。
    //  通常，你会创建一个工厂类，它包含一个负责实现不同对象的方法，如下所示：

    public static Product createProduct(String name){
        switch(name){
            case "loan": return new Loan();
            case "stock": return new Stock();
            case "bond": return new Bond();
            default: throw new RuntimeException("No such product " + name);
        }
    }

}
