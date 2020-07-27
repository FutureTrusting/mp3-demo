package com.fengwenyi.mp3demo.effective.thirdchapter;

import com.fengwenyi.mp3demo.util.BigDecimalUtil;

/**
 * @author : Caixin
 * @date 2019/10/21 17:05
 */
public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof Point)) {
//            return false;
//        }
//        Point p = (Point) o;
//        return p.x == x && p.y == y;
//    }


    //你可能听说过，在 equals 方法中用 getClass 测试代替 instanceof 测试，可以扩展可实例化的类和增加新的值组件，同时保留 equals 约定：

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Point p = (Point) o;
        return p.x == x && p.y == y;
    }


    public static void main(String[] args) {
        Double price = 110.00000;
        String suffix2 = BigDecimalUtil.suffix2(String.valueOf(price));
        System.err.println("0".equalsIgnoreCase(suffix2));
    }
}
