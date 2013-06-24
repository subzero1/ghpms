package com.netsky.base.flow.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.netsky.base.utils.DateFormatUtil;

public class ObjectUtil {
	
	/**
	 * 赋值给对象属性
	 * @param o
	 * @param propertyName
	 *            属性名称,不区分大小写
	 * @param value
	 *            属性值,需要与属性类型一致
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static void setProperty(Object o, String propertyName, Object value) throws IllegalArgumentException,
			IllegalAccessException {
		Field[] fields = o.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equalsIgnoreCase(propertyName)) {
				fields[i].setAccessible(true);
				fields[i].set(o, value);
			}
		}
	}	
	
	/**
	 * 获取对象中的属性值
	 * 
	 * @param o
	 * @param property
	 *            属性名称不区分大小写
	 * @return Object 结果
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static Object getProperty(Object o, String property) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Field[] fields = o.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equalsIgnoreCase(property)) {
				fields[i].setAccessible(true);
				return fields[i].get(o);
			}
		}
		return null;
	}
	
	/**
	 * 复制不同对象中相同名称,相同类型的私有属性

	 * @param fatherObject
	 *            父对象
	 * @param target
	 *            被操作对象
	 * @param notCopy[]
	 *            不复制的属性名称
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static void copyProperty(Object source, Object target, String notCopy[]) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		
		for (Field sourceField : source.getClass().getDeclaredFields()) {
			for (Field targetField :target.getClass().getDeclaredFields()) {
				if (sourceField.getName().equals(targetField.getName())
						&& sourceField.getType().equals(targetField.getType())) {
					boolean copyflag = true;
					if (notCopy != null) {
						for (int k = 0; k < notCopy.length; k++) {
							if (notCopy[k].equals(sourceField.getName())) {
								copyflag = false;
								break;
							}
						}
					}
					if(copyflag ){
						targetField.setAccessible(true);
						sourceField.setAccessible(true);
						targetField.set(target, sourceField.get(source));
						break;
					}
				}
			}
		}
	}



	/**
	 * 对象的属性从Map中加载
	 * <br>对象属性名称，和Map的键，相同；
	 * <br>且值和属性的类型相同时加载
	 * @param source
	 * @param target
	 * @param notCopy
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException void
	 */
	public static void loadProperty(Map map, Object target, String notCopy[]) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Field targetField[] = target.getClass().getDeclaredFields();
		for (Field field:targetField) {
			boolean copyflag = true;
			if (notCopy != null) {
				for (String filedName :notCopy) {
					if (field.getName().equals(filedName)) {
						copyflag = false;
						break;
						
					}
				}
			}
		}
	}
	
	/**
	 * 执行bean中指定方法，支持参数类型
	 * "java.lang.Integer"，"java.lang.Long"，"java.lang.String"，"java.util.Map"，"java.util.Date"
	 * 
	 * @param o
	 *            指定的bean
	 * @param method
	 *            指定方法
	 * @param property[]
	 *            参数数组,按方法参数顺序存放
	 * @param fromCode
	 *            String类型变量调用方法前如需要转码时指定的源编码,为null时不转换
	 * @param targetCode
	 *            String类型变量调用方法前如需要转码时指定的目标编码,为null时不转换
	 * 
	 * @return boolean 被执行方法参数不为null或不为""时返回true，被执行方法名称为"setId"时，返回false
	 * @throws Exception
	 * 
	 */
	public static Object invoke(Object o, Method method, Object paras[])
			throws Exception {
		/**
		 * 根据方法参数类型构造invoke方法参数：Object[]
		 */
		Class<?> clazz[] = method.getParameterTypes();// 获取该方法所有参数类型
		if (paras == null || clazz.length != paras.length) {
			throw new Exception("方法参数数量与所给参数数量不匹配！");
		}
		Object[] object = new Object[clazz.length];
		for (int i = 0; i < clazz.length; i++) {
			if (paras[i] != null) {
				if (clazz[i].getName().equals("java.lang.Integer")) {
					object[i] = paras[i] instanceof Integer? paras[i]: convertUtil.toInteger(paras[i]);
				} else if (clazz[i].getName().equals("java.lang.Long")) {
					object[i] = paras[i] instanceof Long? paras[i]: convertUtil.toLong(paras[i]);
				} else if (clazz[i].getName().equals("java.lang.String")) {
					object[i] = paras[i] instanceof String? paras[i]:paras[i].toString();
				} else if (clazz[i].getName().equals("java.util.Map")) {
					object[i] = paras[i] instanceof Map? paras[i]: new HashMap();
				} else if (clazz[i].getName().equals("java.util.Date")) {
					object[i] = paras[i] instanceof Date? paras[i]:new Date();
				}
			}
		}
		return	method.invoke(o, object);
	}
}
