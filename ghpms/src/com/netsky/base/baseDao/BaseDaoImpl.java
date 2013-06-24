package com.netsky.base.baseDao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.netsky.base.baseObject.QueryBuilder;
import com.netsky.base.baseObject.ResultObject;



/**
 * 基础Dao实现类
 * 
 * @author Chiang
 */
public class BaseDaoImpl extends HibernateDaoSupport implements Dao {
	

	public Object getObject(Class<?> clazz, Serializable id) {
		Object o = getHibernateTemplate().get(clazz, id);
		return o;
	}

	public List<?> getObjects(Class<?> clazz) {
		List<?> list = getHibernateTemplate().loadAll(clazz);
		return list;
	}

	public void removeObject(Class<?> clazz, Serializable id) {
		getHibernateTemplate().delete(getObject(clazz, id));

	}

	public Object saveObject(Object o) {
		getHibernateTemplate().saveOrUpdate(o);
		return o;
	}

	public Object[] saveObject(Object[] o) throws Exception {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			for (int i = 0; i < o.length; i++) {
				session.saveOrUpdate(o[i]);
			}
		} catch (Exception e) {
			tx.rollback();
			throw new Exception("多记录保存出错!" + e);
		} finally {
			session.flush();
			tx.commit();
			session.close();
		}
		return o;
	}

	public List<?> search(QueryBuilder queryBuilder) {
		return getHibernateTemplate().findByCriteria(queryBuilder.getDetachedCriteria());
	}

	public List<?> search(String HSql) {
		return getHibernateTemplate().find(HSql);
	}

	public void update(String HSql) {
		getHibernateTemplate().bulkUpdate(HSql);
	}

	public ResultObject searchByPage(QueryBuilder queryBuilder, int page, int pageSize) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = queryBuilder.getDetachedCriteria().getExecutableCriteria(session);
		int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = (page - 1) * pageSize;
		List<?> list = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
		ResultObject ro = new ResultObject(list, queryBuilder.getClazz());
		ro.setTotalRows(totalCount,pageSize);
		session.close();
		return ro;
	}

	public ResultObject searchByPage(String HSql, int page, int pageSize) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(HSql);
		int startIndex = (page - 1) * pageSize;
		List<?> list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
		ResultObject ro = new ResultObject(list, HSql);
		query = session.createQuery(HSql);
		ro.setTotalRows(query.list().size(),pageSize);
		session.close();
		return ro;
	}
	
	public Session getHibernateSession(){
		return getHibernateTemplate().getSessionFactory().openSession();
	}

	public void removeObject(Object o) {
		getHibernateTemplate().delete(o);	
	}

	public List<?> search(String queryString, Object[] values) {
		return getHibernateTemplate().find(queryString, values);
	}

	public int update(String Hsql, Object[] objs) {
		 return getHibernateTemplate().bulkUpdate(Hsql, objs);
	}
}
