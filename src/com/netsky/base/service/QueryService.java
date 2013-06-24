package com.netsky.base.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;

/**
 * 查询服务接口
 * 
 * @author Chiang 2009-3-11
 */
public interface QueryService {

	/**
	 * 通过queryBuilder查询.
	 * 
	 * @param queryBuilder
	 * @return ResultObject
	 */
	public ResultObject search(QueryBuilder queryBuilder);

	/**
	 * 通过hsql查询
	 * 
	 * @param HSql
	 * @return ResultObject
	 */
	public ResultObject search(String HSql);

	/**
	 * 通过queryBuilder查询.
	 * 
	 * @param queryBuilder
	 * @return List
	 */
	public List<?> searchList(QueryBuilder queryBuilder);
	
	/**
	 * 通过Class 获得 所有对象List
	 * @param clazz
	 * @return
	 */
	public List<?> searchList(Class<?> clazz);
	
	/**
	 * 通过hsql查询
	 * 
	 * @param HSql
	 * @return List
	 */
	public List<?> searchList(String HSql);

	/**
	 * 通过hsql查询
	 * 
	 * @param HSql
	 * @return List
	 */
	public List<?> searchList( String queryString, Object[] values);
	
	/**
	 * 通过id查询.
	 * 
	 * @param clazz
	 * @param id
	 * @return Object
	 */
	public Object searchById(Class<?> clazz, Serializable id);

	/**
	 * 分页查询.
	 * 
	 * @param queryBuilder
	 * @param page
	 *            当前页,从1开始
	 * @param pageSize
	 *            每页记录条数
	 * @return ResultObject
	 */
	public ResultObject searchByPage(QueryBuilder queryBuilder, int page, int pageSize);

	/**
	 * 分页查询.
	 * 
	 * @param HSql
	 * @param page
	 *            当前页,从1开始
	 * @param pageSize
	 *            每页记录条数
	 * @return ResultObject
	 */
	public ResultObject searchByPage(String HSql, int page, int pageSize);
	
	public HibernateTemplate getHibernateTemplate();
	
}
