package com.lichee.core.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

/**
 * CrudDao 基础Dao inf.
 * 
 * @param <T> DAO操作的对象类型
 * @param <PK> 主键类型
 * 
 * @author lichee
 */
public interface CrudDao<T, PK extends Serializable> {

	/**
	 * 保存新增或修改的对象.
	 * 
	 * @param entity 对象必须是session中的对象或含id属性的transient对象.
	 */
	void save(final T entity);
	
	/**
	 * 合并修改的对象.
	 * 
	 * @param entity 对象必须是session中的对象或含id属性的transient对象.
	 */
	void merge(final T entity);
	
	/**
	 * 删除对象.
	 * 
	 * @param entity 对象必须是session中的对象或含id属性的transient对象.
	 */
	void delete(final T entity);
	
	/**
	 * 按id删除对象.
	 * 
	 * @param id 对象id.
	 */
	void delete(PK id);
	
	/**
	 * 按id获取对象，get.
	 * 
	 * @param id 对象id.
	 */
	T get(PK id);
	
	/**
	 * 按id获取对象，get.
	 * 
	 * @param id 对象id.
	 * @param queryFromCache 是否使用缓存
	 */
	T get(PK id, boolean queryFromCache);

	/**
	 * 按id获取对象，load.
	 * 
	 * @param id 对象id.
	 */
	T load(PK id);
	
	/**
	 * 按id列表获取对象列表.
	 * 
	 * @param ids id列表.
	 */
	List<T> get(final Collection<PK> ids);
	
	/**
	 * 获取全部对象.
	 */
	List<T> getAll();
	
	/**
	 * 获取全部对象, 支持按属性行序.
	 *
	 * @param orderByProperty 排序字段.
	 * @param isAsc 是否顺序.
	 */
	List<T> getAll(String orderByProperty, boolean isAsc);
	
	/**
	 * 按属性查找对象列表, 匹配方式为相等.
	 * 
	 * @param propertyName 属性.
	 * @param value 对象值.
	 */
	List<T> findBy(final String propertyName, final Object value);
	
	/**
	 * 按属性查找唯一对象, 匹配方式为相等.
	 * 
	 * @param propertyName 属性.
	 * @param value 对象值.
	 */
	T findUniqueBy(final String propertyName, final Object value);
	
	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param hql hql查询语句.
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	<X> List<X> find(final String hql, final Object... values);
	
	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param hql hql查询语句.
	 * @param values 命名参数,按名称绑定.
	 */
	<X> List<X> find(final String hql, final Map<String, ?> values);
	
	/**
	 * 按SQL查询对象列表.
	 * 
	 * @param sql sql查询语句.
	 * @param className 绑定bean.
	 */
	<X> List<X> findSQL(final String sql, final Class className);
	
	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param hql hql查询语句.
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	<X> X findUnique(final String hql, final Object... values);
	
	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param hql hql查询语句.
	 * @param values 命名参数,按名称绑定.
	 */
	<X> X findUnique(final String hql, final Map<String, ?> values);
	
	/**
	 * 按SQL查询唯一对象.
	 * 
	* @param sql sql语句.
	 * @param className 绑定bean.
	 */
	<X> X findSQLUnique(final String sql);
	
	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @param hql hql查询语句.
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return 更新记录数.
	 */
	int batchExecute(final String hql, final Object... values);
	
	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @param hql hql查询语句.
	 * @param values 命名参数,按名称绑定.
	 * @return 更新记录数.
	 */
	int batchExecute(final String hql, final Map<String, ?> values);
	
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	Query createQuery(final String queryString, final Object... values);
	
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values 命名参数,按名称绑定.
	 */
	Query createQuery(final String queryString, final Map<String, ?> values);
	
	/**
	 * 根据查询SQL与参数列表创建Query对象.
	 * 
	 * @param sql sql语句.
	 */
	Query createSQLQuery(final String sql);
	
	/**
	 * 根据查询SQL与参数列表创建Query对象.
	 * 
	 * @param sql sql语句.
	 * @param className 绑定bean.
	 */
	Query createSQLQuery(final String sql, final Class className);
	
	/**
	 * 根据查询SQL与参数列表创建SQLQuery对象.
	 * 
	 * @param sql sql语句.
	 * @param className 绑定bean.
	 */
	Query sqlCreateQuery(final String sqlString, final Class className);
	
	/**
	 * 根据查询SQL与参数列表创建Query对象.
	 * 
	 * @param sql sql语句.
	 */
	int executeSQL(String sql);
	
	/**
	 * Flush当前Session.
	 */
	void flush();
	
	/**
	 * refresh当前Session.
	 */
	void refresh(final T entity);
	
	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 * 
	 * @param propertyName 属性值
	 * @param newValue 新值
	 * @param oldValue 老值
	 */
	boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue);

}
