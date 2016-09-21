package com.prowo.rop.annotation;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiMethod {

    /**
     * 映射的方法
     *
     * @return
     */
    String method() default "";

    /**
     * 支持的api版本
     *
     * @return
     */
    String version() default "ALL";

    /**
     * 是否缓存结果
     * 缓存时间2分钟
     * 会根据method 为memcached 的key  来缓存。
     * 请谨慎使用
     *
     * @return
     */
    boolean cached() default false;

    /**
     * aop arround 环绕处理class
     *
     * @return
     */
    String[] InterceptorRef() default {};

    /**
     * 每分钟访问限制 默认
     */
    int limitCount() default 999999999;

    /**
     * 访问限制
     * internal 表示内网
     *
     * @return
     */
    String accessRole() default "";

    /**
     * @Description: 默认不忽略次图片验证拦截器
     */
    boolean ignoreValidateInterceptor() default false;
}
