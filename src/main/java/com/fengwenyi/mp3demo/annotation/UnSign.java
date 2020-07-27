package com.fengwenyi.mp3demo.annotation;

import java.lang.annotation.*;

/**
 * @author : Caixin
 * @date 2019/8/2 9:55
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface UnSign {

    String description() default "";
}
