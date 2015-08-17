package com.lichee.core.repository.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.lichee.core.repository.CrudDao;
import com.lichee.core.utils.ReflectionUtils;

/**
 * CrudDaoImpl 基础Dao impl.
 * 
 * @param <T> DAO操作的对象类型
 * @param <PK> 主键类型
 * 
 * @author lichee
 */
@SuppressWarnings("unchecked")
public class CrudDaoImpl<T, PK extends Serializable> implements CrudDao<T, PK> {
	
	protected Logger logger = LoggerFactory.getLogger(CrudDaoImpl.class);
	private SessionFactory sessionFactory;
	protected Class<T> entityClass;

	public CrudDaoImpl() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}
	
	public CrudDaoImpl(final SessionFactory sessionFactory, final Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	
	
	public void save(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().saveOrUpdate(entity);
		logger.debug("save entity: {}", entity);
	}
	
	public void merge(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().merge(entity);
		logger.debug("merge entity: {}", entity);
	}

	public void delete(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
		logger.debug("delete entity: {}", entity);
	}
	
	public void delete(final PK id) {
		Assert.notNull(id, "id不能为空");
		delete(get(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
	}
	
	public T get(final PK id) {
		Assert.notNull(id, "id不能为空");
		return (T) getSession().get(entityClass, id);
	}
	
	public T get(final PK id, final boolean queryFromCache) {
		
		Assert.notNull(id, "id不能为空");
		Session session = getSession();
		session.setCacheMode(CacheMode.IGNORE);
		return (T)session.get(entityClass, id);
	}

	public T load(final PK id) {
		Assert.notNull(id, "id不能为空");
		return (T) getSession().load(entityClass, id);
	}

	public List<T> get(final Collection<PK> ids) {
		return find(Restrictions.in(getIdName(), ids));
	}

	public List<T> getAll() {
		return find();
	}

	public List<T> getAll(String orderByProperty, boolean isAsc) {
		Criteria c = createCriteria();
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if (isAsc) {
			c.addOrder(Order.asc(orderByProperty));
		} else {
			c.addOrder(Order.desc(orderByProperty));
		}
		return c.list();
	}

	public List<T> findBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}

	public T findUniqueBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}

	public <X> List<X> find(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}

	public <X> List<X> find(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).list();
	}
	
	public <X> List<X> findSQL(final String sqlString, final Class className) {
		return createSQLQuery(sqlString, className).list();
	}

	public <X> X findUnique(final String hql, final Object... values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	public <X> X findUnique(final String hql, final Map<String, ?> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}
	
	public <X> X findSQLUnique(final String sql) {
		return (X) createSQLQuery(sql).uniqueResult();
	}

	public int batchExecute(final String hql, final Object... values) {
		return createQuery(hql, values).executeUpdate();
	}

	public int batchExecute(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).executeUpdate();
	}

	public Query createQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	public Query createQuery(final String queryString, final Map<String, ?> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}
	
//	public Query createQuery(final String queryString,
//			final Map<String, ?> values, boolean queryFromCache) {
//		
//		Assert.hasText(queryString, "queryString不能为空");
//		Query query = getSession().createQuery(queryString);
//		if (!queryFromCache) {
//			query.setCacheMode(CacheMode.IGNORE);
//		}
//		if (values != null) {
//			query.setProperties(values);
//		}
//		return query;
//	}
	
	
	public Query createSQLQuery(final String sqlString) {
		Assert.hasText(sqlString, "sqlString不能为空");
		Query query = getSession().createSQLQuery(sqlString);
		return query;
	}
	
	public Query createSQLQuery(final String sqlString, final Class className) {
		Assert.hasText(sqlString, "sqlString不能为空");
		Query query = getSession().createSQLQuery(sqlString).addEntity(className);
		return query;
	}
	
	public int executeSQL(String sqlString) {
		Assert.hasText(sqlString, "sqlString不能为空");
		Query query = this.getSession().createSQLQuery(sqlString);
		return query.executeUpdate();
	}
	
	public void flush() {
		getSession().flush();
	}
	
	public void refresh(final T entity) {
		getSession().refresh(entity);
	}
	
	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue)) {
			return true;
		}
		Object object = findUniqueBy(propertyName, newValue);
		return (object == null);
	}
	
	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	/**
	 * 根据Criterion条件创建Criteria.
	 * 与find()函数可进行更加灵活的操作.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 取得对象的主键名.
	 */
	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}

}