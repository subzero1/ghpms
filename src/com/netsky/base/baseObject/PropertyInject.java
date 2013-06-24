package com.netsky.base.baseObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import jxl.Sheet;

import com.netsky.base.utils.DateFormatUtil;

/**
 * 自动注入对象属性通用类
 * 
 * @author Chiang 2009-12-28
 */
public class PropertyInject {

	/**
	 * 通过私有属性set方法自动注入request中内容到类的属性.
	 * 要求request中key值为对象属性名称的大写,当有request中有不同对象相同名称属性时,需要在key值前增加对象名称
	 * 
	 * @param request
	 * @param o
	 *            注入对象
	 * @param index
	 *            注入对象记录在request数组中的位置
	 * @param fromCode
	 *            request 获取编码格式,null时不转换
	 * @param targetCode
	 *            目标编码格式,null时不转换
	 * @return boolean set 是否已对传入对象进行注入,属性名称为ID不记为已注入.
	 * @throws Exception
	 */
	public static boolean inject(HttpServletRequest request, Object o, int index, String fromCode, String targetCode)
			throws Exception {
		boolean set = false;
		Class<?> clazz = o.getClass();
		String objectName = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
		Method method[] = clazz.getDeclaredMethods();
		for (int i = 0; i < method.length; i++) {
			Class<?> clazz1[] = method[i].getParameterTypes();
			if (clazz1.length == 1) {
				if (method[i].getName().indexOf("set") != -1) {
					String property[] = null;
					if (request.getParameterValues(method[i].getName().replaceFirst("set", "").toUpperCase()) != null
							&& request.getParameterValues(method[i].getName().replaceFirst("set", "").toUpperCase()).length > 0) {
						property = new String[] { request.getParameterValues(method[i].getName()
								.replaceFirst("set", "").toUpperCase())[index] };
					} else if (request.getParameterValues(objectName + "."
							+ method[i].getName().replaceFirst("set", "").toUpperCase()) != null
							&& request.getParameterValues(objectName + "."
									+ method[i].getName().replaceFirst("set", "").toUpperCase()).length > 0) {
						property = new String[] { request.getParameterValues(objectName + "."
								+ method[i].getName().replaceFirst("set", "").toUpperCase())[index] };
					}
					if (property != null) {
						if (invoke(o, method[i], property, fromCode, targetCode))
							set = true;
					}
				}
			}
		}
		return set;
	}

	/**
	 * 将对象一某属性值写入对象二某属性中,要求两属性类型一致
	 * 
	 * @param fatherObject
	 *            对象一
	 * @param fatherProperty
	 *            对象一属性名称 不区分大小写
	 * @param o
	 *            对象二
	 * @param perproty
	 *            对象二属性名称 不区分大小写
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static void injectNexus(Object fatherObject, String fatherProperty, Object o, String property)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		setProperty(o, property, getProperty(fatherObject, fatherProperty));
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
		if (o != null) {
			Field[] fields = o.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				if (fields[i].getName().equalsIgnoreCase(property)) {
					fields[i].setAccessible(true);
					return fields[i].get(o);
				}
			}
		}
		return "";
	}

	/**
	 * 赋值给对象属性
	 * 
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
				return;
			}
		}
	}

	/**
	 * 对传入对象创建新的实例，并复制对象所有属性值
	 * 
	 * @param o
	 *            源对象
	 * @return Object 新创建的实例
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public static Object cloneObject(Object o) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Object newObject = o.getClass().newInstance();
		Field field[] = o.getClass().getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			injectNexus(o, field[i].getName(), newObject, field[i].getName());
		}
		return newObject;
	}

	/**
	 * 将excel信息写入给定对象中
	 * 
	 * @param o
	 *            需要注入的对象
	 * @param columnIndex
	 *            存放字段所在列信息，key：字段名称，与o中属性名称一致。value：所在列
	 * @param sheet
	 *            excel工作表
	 * @param row
	 *            当前所在行
	 * @throws Exception
	 */
	public static boolean injectFromExcel(Object o, Map<String, Integer> columnIndex, Sheet sheet, int row)
			throws Exception {
		boolean set = false;
		Class<?> clazz = o.getClass();
		Method method[] = clazz.getDeclaredMethods();
		for (int i = 0; i < method.length; i++) {
			Class<?> clazz1[] = method[i].getParameterTypes();
			if (clazz1.length == 1) {
				if (method[i].getName().indexOf("set") != -1) {
					String property[] = null;
					String colName = method[i].getName().replaceFirst("set", "").toUpperCase();
					Integer cellIndex = columnIndex.get(colName);
					if (cellIndex != null) {
						Cell cell = sheet.getCell(cellIndex.intValue(), row);
						if (cell.getContents() != null && cell.getContents().length() > 0) {
							property = new String[] { cell.getContents() };
						}
					}
					if (property != null) {
						if (invoke(o, method[i], property, "GBK", "GBK"))
							set = true;
					}
				}
			}
		}
		return set;
	}

	/**
	 * 获取属性类型
	 * 
	 * @param clazz
	 *            类
	 * @param propertyName属性名,不区分大小写
	 * 
	 * @return Class 属性类型
	 */
	public static Class<?> getPropertyType(Class<?> clazz, String propertyName) {
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equalsIgnoreCase(propertyName)) {
				return fields[i].getType();
			}
		}
		return null;
	}

	/**
	 * 复制不同对象中相同名称,相同类型的私有属性,通过get,set方法
	 * 
	 * @param fatherObject
	 *            父对象
	 * @param o
	 *            被操作对象
	 * @param notCopy[]
	 *            不复制的属性名称
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static void copyProperty(Object fatherObject, Object o, String notCopy[]) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Field fatherField[] = fatherObject.getClass().getDeclaredFields();
		Field field[] = o.getClass().getDeclaredFields();
		for (int i = 0; i < fatherField.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[j].getName().equals(fatherField[i].getName())) {
					boolean copyflag = true;
					if (notCopy != null) {
						for (int k = 0; k < notCopy.length; k++) {
							if (notCopy[k].equals(field[j].getName())) {
								copyflag = false;
								break;
							}
						}
					}
					if (copyflag && fatherField[i].getType().getName().equals(field[i].getType().getName())) {
						injectNexus(fatherObject, fatherField[i].getName(), o, fatherField[i].getName());
					}
				}
			}
		}
	}

	/**
	 * 执行bean中指定方法，支持参数类型"java.lang.Integer"，"java.lang.Long"，"java.lang.String"，"java.lang.Double"，"java.util.Date"
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
	public static boolean invoke(Object o, Method method, String property[], String fromCode, String targetCode)
			throws Exception {
		boolean set = false;
		/**
		 * 根据方法参数类型构造invoke方法参数：Object[]
		 */
		Class<?> clazz[] = method.getParameterTypes();// 获取该方法所有参数类型
		if (property == null || clazz.length != property.length) {
			throw new Exception("方法参数数量与所给参数数量不匹配！");
		}
		Object[] object = new Object[clazz.length];
		for (int i = 0; i < clazz.length; i++) {
			if (property[i] != null) {
				if (clazz[i].getName().equals("java.lang.Integer")) {
					if (property[i].length() > 0) {
						object[i] = Integer.valueOf(property[i]);
					}
				} else if (clazz[i].getName().equals("java.lang.Long")) {
					if (property[i].length() > 0)
						object[i] = Long.valueOf(property[i]);
				} else if (clazz[i].getName().equals("java.lang.String")) {
					byte[] fromBytes;
					String targetStr;
					if (fromCode != null) {
						fromBytes = property[i].getBytes(fromCode);
					} else {
						fromBytes = property[i].getBytes();
					}
					if (targetCode != null) {
						targetStr = new String(fromBytes, targetCode);
					} else {
						targetStr = new String(fromBytes);
					}
					object[i] = targetStr;
				} else if (clazz[i].getName().equals("java.lang.Double")) {
					if (property[i].length() > 0)
						object[i] = Double.valueOf(property[i]);
				} else if (clazz[i].getName().equals("java.util.Date")) {
					if (property[i].length() > 0) {
						if (property[i].indexOf("-") != -1) {
							if (property[i].indexOf(":") == -1) {
								property[i] = property[i] + " 00:00:00";
							} else {
								if (property[i].split(" ").length > 0 && property[i].split(" ")[1].length() < 8) {
									if (property[i].split(" ")[1].length() == 5) {
										property[i] = property[i] + ":00";
									} else if (property[i].split(" ")[1].length() == 2) {
										property[i] = property[i] + ":00:00";
									}
								}
							}
							object[i] = DateFormatUtil.FormatTimeString(property[i]);
						} else {
							object[i] = DateFormatUtil.ForamteString(property[i],"yyyymmdd");
						}

					}
				}
				if (!method.getName().equalsIgnoreCase("setId") && property[i].length() > 0) {
					set = true;
				}
			}
		}
		method.invoke(o, object);
		return set;
	}
}
