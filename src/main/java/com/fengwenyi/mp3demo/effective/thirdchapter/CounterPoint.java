package com.fengwenyi.mp3demo.effective.thirdchapter;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : Caixin
 * @date 2019/10/22 10:50
 */
public class CounterPoint extends Point {

    private static final AtomicInteger counter = new AtomicInteger();

    public CounterPoint(int x, int y) {
        super(x, y);
        counter.incrementAndGet();
    }

    public static int numberCreated() {
        return counter.get();
    }


//    private static final Set<Point> unitCircle = Set.of(
//            new Point(1, 0), new Point(0, 1),
//            new Point(-1, 0), new Point(0, -1)
//    );

//    public static boolean onUnitCircle(Point point) {
//        return unitCircle.contains(point);
//    }
}

