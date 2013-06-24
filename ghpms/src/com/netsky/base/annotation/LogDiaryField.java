package com.netsky.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * java注解类，用于标识需要记录操作日志的持久化对象私有字段 用于java类的注解
 * 
 * @author Chiang 2010-01-06
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogDiaryField {
	String value() default "";
}   
   