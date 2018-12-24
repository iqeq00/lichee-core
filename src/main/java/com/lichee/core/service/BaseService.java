package com.lichee.core.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.lichee.core.repository.vo.Page;

/**
 * BaseService 基础service inf.
 * 
 * @param <T> DAO操作的对象类型
 * @param <PK> 主键类型
 * 
 * @author lichee
 */
public interface BaseService<T, PK extends Serializable> {

	/**
	 * 保存新增或修改的对象.
	 * 
	 * @param entity 对象必须是session中的对象或含id属性的transient对象.
	 */
	void save(T entity);

	/**
	 * 删除对象.
	 * 
	 * @param entity 对象必须是session中的对象或含id属性的transient对象.
	 */
	void delete(T entity);

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
	List<T> get(Collection<PK> ids);
	
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
	List<T> findBy(String propertyName, Object value);
	
	/**
	 * 按属性查找唯一对象, 匹配方式为相等.
	 * 
	 * @param propertyName 属性.
	 * @param value 对象值.
	 */
	T findUniqueBy(String propertyName, Object value);
	
	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param hql hql查询语句.
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	<X> List<X> find(String hql, Object... values);
	
	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param hql hql查询语句.
	 * @param values 命名参数,按名称绑定.
	 */
	<X> List<X> find(String hql, Map<String, ?> values);
	
	/**
	 * 按SQL查询对象列表.
	 * 
	 * @param sql sql查询语句.
	 * @param className 绑定bean.
	 */
	<X> List<X> findSQL(String sql, Class className);
	
	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param hql hql查询语句.
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	<X> X findUnique(String hql, Object... values);
	
	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param hql hql查询语句.
	 * @param values 命名参数,按名称绑定.
	 */
	<X> X findUnique(String hql, Map<String, ?> values);
	
	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @param hql hql查询语句.
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return 更新记录数.
	 */
	int batchExecute(String hql, Object... values);
	
	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @param hql hql查询语句.
	 * @param values 命名参数,按名称绑定.
	 * @return 更新记录数.
	 */
	int batchExecute(String hql, Map<String, ?> values);
	
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	Query createQuery(String queryString, Object... values);
	
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values 命名参数,按名称绑定.
	 */
	Query createQuery(String queryString, Map<String, ?> values);
	
	/**
	 * 根据查询SQL与参数列表创建Query对象.
	 * 与find()函数可进行更加灵活的操作.
	 * 
	 * @param sql sql查询语句.
	 * @param className 绑定bean.
	 */
	Query createSQLQuery(String sqlString, Class className);
	
	/**
	 * 根据查询SQL与参数列表创建SQLQuery对象.
	 * 与find()函数可进行更加灵活的操作.
	 * 
	 * @param sql sql查询语句.
	 * @param className 绑定bean.
	 */
	Query sqlCreateQuery(String sqlString, Class className);
	
	/**
	 * 执行一句sql
	 * 
	 * @param sql sql查询语句.
	 */
	void executeSQL(String sql);
	
	/**
	 * Flush当前Session.
	 */
	void flush();
	
	/**
	 * refresh当前Session.
	 */
	void refresh(T entity);
	
	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 * 
	 * @param propertyName 属性值
	 * @param newValue 新值
	 * @param oldValue 老值
	 */
	boolean isPropertyUnique(String propertyName, Object newValue, Object oldValue);
	
	/**
	 * 分页获取全部对象.
	 */
	Page<T> getAll(Page<T> page);
	
	/**
	 * 按HQL分页查询.
	 * 
	 * @param page 分页参数. 注意不支持其中的orderBy参数.
	 * @param hql hql语句.
	 * @param values 数量可变的查询参数,按顺序绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参数.
	 */
	Page<T> findPage(Page<T> page, String hql, Object... values);
	
	/**
	 * 按HQL分页查询.
	 * 
	 * @param page 分页参数. 注意不支持其中的orderBy参数.
	 * @param hql hql语句.
	 * @param values 命名参数,按名称绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参数.
	 */
	Page<T> findPage(Page<T> page, String hql, Map<String, ?> values);
	
	
	/**
	 * 按HQL分页查询Bean.
	 * 
	 * @param page 分页参数. 注意不支持其中的orderBy参数.
	 * @param hql hql语句.
	 * @param values 命名参数,按名称绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参数.
	 */
	Page<Object> findPageBean(Page<Object> page, String hql, Map<String, ?> values);
	
	List<Object> listByBean(final String hql, final Map<String, ?> values);

}