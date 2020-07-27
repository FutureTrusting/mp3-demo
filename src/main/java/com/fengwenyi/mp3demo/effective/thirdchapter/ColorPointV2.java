package com.fengwenyi.mp3demo.effective.thirdchapter;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.Objects;

/**
 * @author : Caixin
 * @date 2019/10/22 10:26
 */
@Slf4j
public class ColorPointV2 {

    private final Color color;
    private final Point point;


    public ColorPointV2(int x, int y, Color color) {
        point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    public Point asPoint() {
        return point;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ColorPointV2)) {
            return false;
        }
        ColorPointV2 cp = (ColorPointV2) o;
        return cp.point.equals(point) && cp.color.equals(color);
    }


    public static void main(String[] args) {
        Point p = new Point(1, 2);
        ColorPointV2 colorPoint = new ColorPointV2(1, 2, Color.RED);
        System.err.println(p.equals(colorPoint)); //true
        System.err.println(colorPoint.equals(p)); //false

        //这种方法确实提供了对称性，但是却牺牲了传递性：
        ColorPointV2 p1 = new ColorPointV2(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPointV2 p3 = new ColorPointV2(1, 2, Color.BLUE);

        System.err.println(p1.equals(p2));  //true
        System.err.println(p2.equals(p3));  //true
        System.err.println(p1.equals(p3));  //false

    }

}
