package com.omcube.comm.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Conductor {

    /**
     * 配置文件方式配置
     * @return
     */
    String value() default "";


}
