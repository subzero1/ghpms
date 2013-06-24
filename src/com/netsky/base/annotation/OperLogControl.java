package com.netsky.base.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.netsky.base.baseObject.PropertyInject;

/**
 * 处理操作日，判断当前对象是否需要记录日志以及日志的格式化
 * 
 * @author Chiang 2010-02-06
 */
public class OperLogControl {

	/**
	 * @param o
	 *            持久化对象
	 * @return 是否需要记录日志
	 */
	public static String judgType(Object o) {
		Class<? extends Object> clazz = o.getClass();
		if (clazz.isAnnotationPresent(LogDiaryType.class)) {
			return clazz.getAnnotation(LogDiaryType.class).value();
		}
		return null;
	}

	/**
	 * 根据DiaryField注解生成持久化对象的日志记录，格式：field1=var1;field2=var2
	 * 
	 * @param o
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static String StructLog(Object o) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		String str = "";
		Class<? extends Object> clazz = o.getClass();
		Field[] field = clazz.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			if (field[i].isAnnotationPresent(LogDiaryField.class)) {
				str += ";"
						+ ("".equals(field[i].getAnnotation(LogDiaryField.class).value()) ? field[i].getName()
								: field[i].getAnnotation(LogDiaryField.class).value())
						+ "="
						+ (PropertyInject.getProperty(o, field[i].getName().toUpperCase()) != null ? PropertyInject
								.getProperty(o, field[i].getName().toUpperCase()) : "");

			}
		}
		if (str.length() > 1) {
			str = str.substring(1);
		}
		return str;
	}

}
