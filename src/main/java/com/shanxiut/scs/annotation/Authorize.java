package com.shanxiut.scs.annotation;

import java.lang.annotation.*;

/**
 * @author LiHaitao
 * @description Authorize:
 * @date 2019/3/7 16:02
 **/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Authorize {
    String[] resources() default {};

    String[] roles() default {};

    String message() default "无权限访问";

    boolean dataAccess() default true;

    String[] creatorColumn() default {};

    String[] departmentColumn() default {};
}

