package com.lichee.core.repository;

import java.io.Serializable;
import java.util.Map;

import com.lichee.core.repository.vo.Page;

/**
 * BaseDao 基础Dao inf.
 * 
 * @param <T> DAO操作的对象类型
 * @param <PK> 主键类型
 * 
 * @author lichee
 */
public interface BaseDao<T, PK extends Serializable> extends CrudDao<T, PK> {

	/**
	 * 分页获取全部对象.
	 */
	Page<T> getAll(final Page<T> page);
	
	/**
	 * 按HQL分页查询.
	 * 
	 * @param page 分页参数. 注意不支持其中的orderBy参数.
	 * @param hql hql语句.
	 * @param values 数量可变的查询参数,按顺序绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参数.
	 */
	Page<T> findPage(final Page<T> page, final String hql, final Object... values);
	
	/**
	 * 按HQL分页查询.
	 * 
	 * @param page 分页参数. 注意不支持其中的orderBy参数.
	 * @param hql hql语句.
	 * @param values 命名参数,按名称绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参数.
	 */
	Page<T> findPage(final Page<T> page, final String hql, final Map<String, ?> values);
	
	/**
	 * 按HQL分页查询Bean.
	 * 
	 * @param page 分页参数. 注意不支持其中的orderBy参数.
	 * @param hql hql语句.
	 * @param values 命名参数,按名称绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参数.
	 */
	Page<Object> findPageBean(final Page<Object> page, final String hql, final Map<String, ?> values);
	
	
}
