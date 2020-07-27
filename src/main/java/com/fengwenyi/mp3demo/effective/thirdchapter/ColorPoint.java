package com.fengwenyi.mp3demo.effective.thirdchapter;

import cn.hutool.core.collection.CollectionUtil;
import com.google.gson.Gson;
import org.apache.commons.collections4.CollectionUtils;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author : Caixin
 * @date 2019/10/22 10:26
 */
public class ColorPoint extends Point {

    private final Color color;



    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof ColorPoint)) {
//            return false;
//        }
//        return super.equals(o) && ((ColorPoint) o).color.equals(o);
//    }

    //Broken - violates transitivity!

//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof Point)) {
//            return false;
//        }
//        if (!(o instanceof ColorPoint)) {
//            return o.equals(this);
//        }
//        return super.equals(o) && ((ColorPoint) o).color == color;
//    }




    public static void main(String[] args) {
        Point p = new Point(1, 2);
        ColorPoint colorPoint = new ColorPoint(1, 2, Color.RED);
        System.err.println(p.equals(colorPoint)); //true
        System.err.println(colorPoint.equals(p)); //false

        //这种方法确实提供了对称性，但是却牺牲了传递性：
        ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);

        System.err.println(p1.equals(p2));  //true
        System.err.println(p2.equals(p3));  //true
        System.err.println(p1.equals(p3));  //false

        Integer[] integers = new Integer[2];
        integers[0]=1;
        integers[1]=2;
        Integer[] integer2 = new Integer[2];
        integer2[0]=1;
        integer2[1]=2;
        boolean equals = Arrays.equals(integers, integer2);
        System.err.println("equals"+equals);


        List<Object> objects = CollectionUtil.toList();
        objects.add(1);
        objects.add(2);
        objects.add(3);

        List<Integer> arrayList = CollectionUtil.toList(1, 2, 4);
        System.err.println(new Gson().toJson(arrayList));


        List<Object> arrayList1 = CollectionUtil.toList();
        System.err.println(arrayList1);


    }

}
