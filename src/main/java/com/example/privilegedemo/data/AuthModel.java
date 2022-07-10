package com.example.privilegedemo.data;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AuthModel {
    String[] codeList();
    String description() default "";
}
