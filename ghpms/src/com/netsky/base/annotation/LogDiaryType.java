package com.netsky.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * java注解类，用于标识需要记录操作日志的持久化对象 用于java类的注解
 * 
 * @author Chiang 2010-01-06
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogDiaryType {
	String value() default "";
}
