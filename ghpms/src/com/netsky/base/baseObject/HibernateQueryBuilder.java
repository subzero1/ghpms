package com.netsky.base.baseObject;

import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Hibernate查询类.
 * 
 * @author Chiang 2009-3-12
 */
public class HibernateQueryBuilder implements QueryBuilder {

	private static final long serialVersionUID = -4448621891083623051L;

	private DetachedCriteria detachedCriteria;

	private Class<?> clazz;

	public HibernateQueryBuilder(Class<?> clazz) {
		this.clazz = clazz;
		detachedCriteria = DetachedCriteria.forClass(clazz);
	}

	public void addCriterion(Criterion criterion) {
		detachedCriteria.add(criterion);

	}

	public void addOrderBy(Order orderBy) {
		detachedCriteria.addOrder(orderBy);
	}

	public void allEq(Map<String, Object> propertyNameValues) {
		if (isNotEmpty(propertyNameValues))
			detachedCriteria.add(Restrictions.allEq(propertyNameValues));
	}

	public void between(String propertyName, Object lowValue, Object highValue) {
		if (isNotEmpty(lowValue) && isNotEmpty(highValue))
			detachedCriteria.add(Restrictions.between(propertyName, lowValue, highValue));
		else if (isNotEmpty(lowValue))
			this.ge(propertyName, lowValue);
		else if (isNotEmpty(highValue))
			this.le(propertyName, highValue);
	}

	public void eq(String propertyName, Object value) {
		if (isNotEmpty(value))
			detachedCriteria.add(Restrictions.eq(propertyName, value));
	}

	public void ge(String propertyName, Object value) {
		if (isNotEmpty(value))
			detachedCriteria.add(Restrictions.ge(propertyName, value));
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	public void gt(String propertyName, Object value) {
		if (isNotEmpty(value))
			detachedCriteria.add(Restrictions.gt(propertyName, value));
	}

	public void in(String propertyName, Object[] values) {
		if (isNotEmpty(values))
			detachedCriteria.add(Restrictions.in(propertyName, values));
	}

	public void isNotNull(String propertyName) {
		detachedCriteria.add(Restrictions.isNotNull(propertyName));

	}

	public void isNull(String propertyName) {
		detachedCriteria.add(Restrictions.isNull(propertyName));

	}

	public void le(String propertyName, Object value) {
		if (isNotEmpty(value))
			detachedCriteria.add(Restrictions.le(propertyName, value));
	}

	public void like(String propertyName, Object value) {
		if (isNotEmpty(value))
			detachedCriteria.add(Restrictions.like(propertyName, value));
	}

	public void notlike(String propertyName, Object value) {
		if (isNotEmpty(value))
			detachedCriteria.add(Restrictions.or(Restrictions.not(Restrictions.like(propertyName, value)), Restrictions
					.isNull(propertyName)));
	}

	public void like(String propertyName, String value, MatchMode matchMode) {
		if (isNotEmpty(value))
			detachedCriteria.add(Restrictions.like(propertyName, value, matchMode));

	}

	public void notlike(String propertyName, String value, MatchMode matchMode) {
		if (isNotEmpty(value))
			detachedCriteria.add(Restrictions.or(Restrictions.not(Restrictions.like(propertyName, value, matchMode)),
					Restrictions.isNull(propertyName)));
	}

	public void lt(String propertyName, Object value) {
		if (isNotEmpty(value))
			detachedCriteria.add(Restrictions.lt(propertyName, value));
	}

	public void notEq(String propertyName, Object value) {
		if (isNotEmpty(value))
			detachedCriteria.add(Restrictions.ne(propertyName, value));
	}

	private boolean isNotEmpty(Object o) {
		if (o != null && o.toString().length() > 0)
			return true;
		else
			return false;
	}
}
