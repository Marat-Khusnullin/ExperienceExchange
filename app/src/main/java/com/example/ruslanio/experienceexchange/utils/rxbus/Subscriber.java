package com.example.ruslanio.experienceexchange.utils.rxbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Ruslanio on 06.11.2017.
 */

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Subscriber {

    int MAIN_THREAD=1;
    int IO_THREAD=2;

    String tag();

    int thread() default MAIN_THREAD;
}

