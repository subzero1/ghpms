package com.netsky.base.baseObject;

import java.io.Serializable;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

/**
 * 通用查询接口.
 * 
 * @author Chiang 2009-3-12
 */
public interface QueryBuilder extends Serializable {

	/**
	 * equals 相等
	 * 
	 * @param propertyName
	 *            列名
	 * @param value
	 *            值
	 */
	public void eq(String propertyName, Object value);

	/**
	 * 不等于
	 * 
	 * @param propertyName
	 *            列名
	 * @param value
	 *            值
	 */
	public void notEq(String propertyName, Object value);

	/**
	 * like 类似
	 * 
	 * @param propertyName
	 *            列名
	 * @param value
	 *            值
	 */
	public void like(String propertyName, Object value);

	/**
	 * not like 不类似
	 * 
	 * @param propertyName
	 *            列名
	 * @param value
	 *            值
	 */
	public void notlike(String propertyName, Object value);

	/**
	 * like 根据MatchMode判断类似
	 * 
	 * @param propertyName
	 *            列名
	 * @param value
	 *            值
	 * @param matchMode
	 *            匹配类型
	 */
	public void like(String propertyName, String value, MatchMode matchMode);

	/**
	 * not like 根据MatchMode判断不类似
	 * 
	 * @param propertyName
	 *            列名
	 * @param value
	 *            值
	 * @param matchMode
	 *            匹配类型
	 */
	public void notlike(String propertyName, String value, MatchMode matchMode);

	/**
	 * allEq 使用Map，使用key/value进行多个比对
	 * 
	 * @param propertyNameValues
	 */
	public void allEq(Map<String, Object> propertyNameValues);

	/**
	 * 大于等于
	 * 
	 * @param propertyName
	 *            列名
	 * @param value
	 *            值
	 */
	public void ge(String propertyName, Object value);

	/**
	 * 大于
	 * 
	 * @param propertyName
	 *            列名
	 * @param value
	 *            值
	 */
	public void gt(String propertyName, Object value);

	/**
	 * 小于等于
	 * 
	 * @param propertyName
	 *            列名
	 * @param value
	 *            值
	 */
	public void le(String propertyName, Object value);

	/**
	 * 小于
	 * 
	 * @param propertyName
	 *            列名
	 * @param value
	 *            值
	 */
	public void lt(String propertyName, Object value);

	/**
	 * 判断是否在给定的区间里
	 * 
	 * @param propertyName
	 *            列名
	 * @param lowValue
	 *            下限
	 * @param highValue
	 *            上限
	 */
	public void between(String propertyName, Object lowValue, Object highValue);

	/**
	 * 判断是否在给定的数组里
	 * 
	 * @param propertyName
	 *            列名
	 * @param values
	 *            值
	 */
	public void in(String propertyName, Object[] values);

	/**
	 * 判断是否为空
	 * 
	 * @param propertyName
	 *            列名
	 */
	public void isNull(String propertyName);

	/**
	 * 判断是否不等于空
	 * 
	 * @param propertyName
	 *            列名
	 */
	public void isNotNull(String propertyName);

	/**
	 * 排序
	 * 
	 * @param orderBy
	 *            排序对象
	 * @see org.hibernate.criterion.Order
	 */
	public void addOrderBy(Order orderBy);

	/**
	 * 直接添加查询条件
	 * 
	 * @param criterion
	 *            查询条件对象
	 * @see org.hibernate.criterion.Criterion
	 */
	public void addCriterion(Criterion criterion);

	/**
	 * 获取hibernate查询对象
	 * 
	 * @return org.hibernate.criterion.DetachedCriteria
	 */
	public DetachedCriteria getDetachedCriteria();

	/**
	 * 获取hibernate持久化对象类
	 * 
	 * @return Class
	 */
	public Class<?> getClazz();

}
