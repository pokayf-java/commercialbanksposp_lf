package com.poka.app.anno.base.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.base.dao.impl.BaseDao;
import com.poka.app.anno.base.service.IBaseService;

public class BaseService<T, PK extends Serializable> implements IBaseService<T, PK> {

	
	protected BaseDao<T, PK> baseDao;
	
	
	public BaseDao<T, PK> getBaseDao() {
		return baseDao;
	}



	@Autowired
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
	public List<T> findByProperty(String propertyName, Object value) {
		
		return baseDao.findByProperty(propertyName, value);
	}

	@Override
	public T findUniqueByProperty(String propertyName, Object value) {
	
		return baseDao.findUniqueByProperty(propertyName, value);
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
	public List<T> find(Criterion... criterions) {
		
		return baseDao.find(criterions);
	}

	@Override
	public T findUnique(Criterion... criterions) {
		
		return baseDao.findUnique(criterions);
	}

	@Override
	public Criteria createCriteria(Criterion... criterions) {
		
		return baseDao.createCriteria(criterions);
	}

	@Override
	public void initProxyObject(Object proxy) {
		baseDao.initProxyObject(proxy);
		
	}

	@Override
	public void flush() {
		baseDao.flush();
		
	}

	@Override
	public Query distinct(Query query) {
		
		return baseDao.distinct(query);
	}

	@Override
	public Criteria distinct(Criteria criteria) {
		
		return baseDao.distinct(criteria);
	}

	@Override
	public String getIdName() {
		
		return baseDao.getIdName();
	}

	@Override
	public boolean isPropertyUnique(String propertyName, Object newValue,
			Object oldValue) {
		
		return baseDao.isPropertyUnique(propertyName, newValue, oldValue);
	}

}
