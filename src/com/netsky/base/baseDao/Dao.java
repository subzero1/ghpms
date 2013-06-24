package com.netsky.base.baseDao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;


/**
 * 基础Dao接口类
 * 
 * @author Chiang 2009-3-11
 */
public interface Dao {

	/**
	 * 插入或保存持久化对象
	 * 
	 * @param o
	 * @return Object 更新结果.
	 */
	public Object saveObject(Object o);

	/**
	 * 插入或保存持久化对象
	 * 
	 * @param o
	 * @return Object 更新结果.
	 */
	public Object[] saveObject(Object[] o) throws Exception;

	/**
	 * 根据id获取持久化对象
	 * 
	 * @param clazz
	 *            持久化对象Class
	 * @param id
	 *            对象主键
	 * @return Object 返回查询结果对象.
	 */
	public Object getObject(Class<?> clazz, Serializable id);

	/**
	 * 获取所有对象
	 * 
	 * @param clazz
	 *            持久化对象Class
	 * @return List 所有记录
	 */
	public List<?> getObjects(Class<?> clazz);

	/**
	 * 根据id删除持久化对象
	 * 
	 * @param clazz
	 *            持久化对象Class
	 * @param id
	 */
	public void removeObject(Class<?> clazz, Serializable id);

	/**
	 * 删除持久化对象
	 * 
	 * @param o
	 *            持久化对象Object
	 */
	public void removeObject(Object o);

	/**
	 * 通过querybuilder查询对象
	 * 
	 * @param queryBuilder
	 * @return List
	 */
	public List<?> search(QueryBuilder queryBuilder);

	/**
	 * 通过hsql查询
	 * 
	 * @param HSql
	 * @return ResultObject
	 */
	public List<?> search(String HSql);
	
	/**
	 * 通过hsql查询，可以得参数；
	 * @author wind 2010.1.15
	 * method:search
	 * @param queryString
	 * @param values
	 * @return List<?>
	 */
	public List<?> search( String queryString, Object[] values);

	/**
	 * 通过hsql更新数据
	 * 
	 * @param HSql
	 */
	public void update(String HSql);
	
	/**
	 * 通过hsql，参数更新数据库	
	 * @param Hsql
	 * @param objs
	 * @return int
	 */
	public int update(String Hsql,Object[] objs);

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

	/**
	 * 获得hibernate session
	 *  2010.01.11 黄钢强修改：getHiberbateSessionn改成getHibernateSession
	 * @return Session session
	 */
	public Session getHibernateSession();
	
	/**
	 * 获得 HibernateTemplate
	 *  2010.01.11 黄钢强新增
	 * @return
	 */
	public HibernateTemplate getHibernateTemplate();

}
