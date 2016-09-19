package com.prowo.common.annotation;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SpVersion {

    /**
     * 用于属性过滤 json输出的时候
     *
     * @return
     */
    int gt() default 0;

    int ipadGt() default 0;
}
