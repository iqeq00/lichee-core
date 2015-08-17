package com.lichee.core.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.lichee.core.repository.BaseDao;
import com.lichee.core.repository.vo.Page;
import com.lichee.core.service.BaseService;


/**
 * BaseServiceImpl 基础service impl.
 * 
 * @param <T> DAO操作的对象类型
 * @param <PK> 主键类型
 * 
 * @author lichee
 */
public class BaseServiceImpl<T, PK extends Serializable> implements
		BaseService<T, PK> {

	public BaseDao<T, PK> baseDao;

	public BaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}

	@Override
	public void delete(T entity) {
		baseDao.delete(entity);
	}

	@Override
	public void delete(PK id) {
		baseDao.delete(id);
	}

	@Override
	public T get(PK id) {
		return baseDao.get(id);
	}
	
	@Override
	public T get(PK id, boolean queryFromCache) {
		return baseDao.get(id, queryFromCache);
	}

	@Override
	public T load(PK id) {
		return baseDao.load(id);
	}

	@Override
	public List<T> get(Collection<PK> ids) {
		return baseDao.get(ids);
	}

	@Override
	public List<T> getAll() {
		return baseDao.getAll();
	}

	@Override
	public List<T> getAll(String orderByProperty, boolean isAsc) {
		return baseDao.getAll(orderByProperty, isAsc);
	}

	@Override
	public List<T> findBy(String propertyName, Object value) {
		return baseDao.findBy(propertyName, value);
	}

	@Override
	public T findUniqueBy(String propertyName, Object value) {
		return baseDao.findUniqueBy(propertyName, value);
	}

	@Override
	public <X> List<X> find(String hql, Object... values) {
		return baseDao.find(hql, values);
	}

	@Override
	public <X> List<X> find(String hql, Map<String, ?> values) {
		return baseDao.find(hql, values);
	}
	
	@Override
	public <X> List<X> findSQL(String sql, Class className) {
		return baseDao.find(sql, className);
	}

	@Override
	public <X> X findUnique(String hql, Object... values) {
		return baseDao.findUnique(hql, values);
	}

	@Override
	public <X> X findUnique(String hql, Map<String, ?> values) {
		return baseDao.findUnique(hql, values);
	}

	@Override
	public int batchExecute(String hql, Object... values) {
		return baseDao.batchExecute(hql, values);
	}

	@Override
	public int batchExecute(String hql, Map<String, ?> values) {
		return baseDao.batchExecute(hql, values);
	}

	@Override
	public Query createQuery(String queryString, Object... values) {
		return baseDao.createQuery(queryString, values);
	}

	@Override
	public Query createQuery(String queryString, Map<String, ?> values) {
		return baseDao.createQuery(queryString, values);
	}
	
	@Override
	public Query createSQLQuery(final String sqlString, final Class className) {
		return baseDao.createSQLQuery(sqlString, className);
	}
	
	@Override
	public void executeSQL(String sql) {
		baseDao.executeSQL(sql);
	}

	@Override
	public void flush() {
		baseDao.flush();
	}
	
	@Override
	public void refresh(T entity) {
		baseDao.refresh(entity);
	}

	@Override
	public boolean isPropertyUnique(String propertyName, Object newValue,
			Object oldValue) {
		return baseDao.isPropertyUnique(propertyName, newValue, oldValue);
	}

	@Override
	public Page<T> getAll(Page<T> page) {
		return baseDao.getAll(page);
	}

	@Override
	public Page<T> findPage(Page<T> page, String hql, Object... values) {
		
		page = baseDao.findPage(page, hql, values);
		if (page.getResult().size() <= 0) {
			page.setPageNo(page.getPageNo() - 1);
			page = baseDao.findPage(page, hql, values);
		}
		return page;
	}

	@Override
	public Page<T> findPage(Page<T> page, String hql, Map<String, ?> values) {
		
		page = baseDao.findPage(page, hql, values);
		if (page.getResult().size() <= 0) {
			page.setPageNo(page.getPageNo() - 1);
			page = baseDao.findPage(page, hql, values);
		}
		return page;
	}
	
	@Override
	public Page<Object> findPageBean(Page<Object> page, String hql, Map<String, ?> values) {
		
		page = baseDao.findPageBean(page, hql, values);
		if (page.getResult().size() <= 0) {
			page.setPageNo(page.getPageNo() - 1);
			page = baseDao.findPageBean(page, hql, values);
		}
		return page;
	}

}