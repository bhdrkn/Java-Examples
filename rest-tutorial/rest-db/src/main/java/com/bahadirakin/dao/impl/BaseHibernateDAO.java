/*
 *   Copyright 2012 Bahadır AKIN
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.bahadirakin.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bahadirakin.util.HibernateUtil;
import com.bahadirakin.dao.IBaseDAO;
import com.bahadirakin.model.IEntity;

/**
 * Contains common operations for all Entities DAO
 * 
 * @author Bahadır AKIN
 * 
 * @param IEntity
 *            Class
 */
@SuppressWarnings("unchecked")
public abstract class BaseHibernateDAO<T extends IEntity> implements
		IBaseDAO<T> {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory
			.getLogger(BaseHibernateDAO.class);

	private HibernateUtil hibernateUtil;
	private Class<T> persistentClass;

	public BaseHibernateDAO() {
		super();
		hibernateUtil = HibernateUtil.getInstance();
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected Session getCurrentSession() {
		return hibernateUtil.getCurrentSession();
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public Serializable save(T entity) {
		Serializable serializable = null;

		if (entity == null) {
			throw new IllegalArgumentException("Entity must not be null");
		}

		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			serializable = session.save(entity);
			transaction.commit();
		} catch (HibernateException e) {
			LOG.error("Error while saving Entity. M: " + e.getMessage()
					+ " C: " + e.getCause(), e);
		}
		return serializable;
	}

	public void saveOrUpdate(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Entity must not be null");
		}

		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(entity);
			transaction.commit();
		} catch (HibernateException e) {
			LOG.error("Error while saveOrUpdate Entity. M: " + e.getMessage()
					+ " C: " + e.getCause(), e);
		}
	}

	public void delete(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Entity Must not be Null");
		}

		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.delete(entity);
			transaction.commit();
		} catch (HibernateException e) {
			LOG.error("Error while delete Entity. M: " + e.getMessage()
					+ " C: " + e.getCause(), e);
		}
	}

	public void detach(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Entity Must not be null");
		}

		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.evict(entity);
			transaction.commit();
		} catch (Exception e) {
			LOG.error("Error while detach Entity. M: " + e.getMessage()
					+ " C: " + e.getCause(), e);
		}
	}

	public void refresh(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Entity Must not be null");
		}

		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.refresh(entity);
			transaction.commit();
		} catch (Exception e) {
			LOG.error("Error while refresh Entity. M: " + e.getMessage()
					+ " C: " + e.getCause(), e);
		}
	}

	public T getById(Integer id) {
		T entity = null;
		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			entity = (T) session.get(getPersistentClass(), id);
			transaction.commit();
		} catch (Exception e) {
			LOG.error("Error while getById Entity. M: " + e.getMessage()
					+ " C: " + e.getCause(), e);
		}
		return entity;
	}

	public List<T> getAll() {
		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			List<T> list = session.createCriteria(getPersistentClass()).list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			LOG.error("Error while getAll Entities. M: " + e.getMessage()
					+ " C: " + e.getCause(), e);
		}
		return null;
	}

	public T getBySql(String query) {
		T entity = null;
		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			entity = (T) session.createSQLQuery(query)
					.addEntity(getPersistentClass()).uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			LOG.error("Error while getWithSql Entity. M: " + e.getMessage()
					+ " C: " + e.getCause() + " SQL: " + query, e);
		}
		return entity;
	}

	public List<T> getAllBySql(String query) {
		List<T> ts = null;
		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			ts = session.createSQLQuery(query).addEntity(getPersistentClass())
					.list();
			transaction.commit();
		} catch (Exception e) {
			LOG.error(
					"Error while getAllWithSql Entities. M: " + e.getMessage()
							+ " C: " + e.getCause() + " SQL: " + query, e);
		}
		return ts;
	}

	public void executeSQLQuery(String query) {
		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.createSQLQuery(query).addEntity(getPersistentClass())
					.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			LOG.error(
					"Error while executeSQLQuery Entities. M: "
							+ e.getMessage() + " C: " + e.getCause() + " SQL: "
							+ query, e);
		}
	}

	protected List<T> findAllByCriteria(Criterion... criterions) {
		return findAllByCriteriaAndOrder(null, criterions);
	}

	protected List<T> findAllByCriteriaAndOrder(Order order,
			Criterion... criterions) {
		List<T> ts = null;
		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			Criteria criteria = createCriteria(session, criterions);
			if (order != null) {
				criteria.addOrder(order);
			}
			ts = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			LOG.error(
					"Error while findAllByCriteria Entities and Order them. M: "
							+ e.getMessage() + " C: " + e.getCause(), e);
		}
		return ts;
	}

	protected T findByCriteria(Criterion... criterions) {
		T t = null;
		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			Criteria criteria = createCriteria(session, criterions);
			t = (T) criteria.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			LOG.error("Error while findByCriteria Entiry. M: " + e.getMessage()
					+ " C: " + e.getCause(), e);
		}
		return t;
	}

	protected Object findByProjection(Projection projection,
			Criterion... criterions) {
		Object result = null;
		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			Criteria criteria = createCriteria(session, criterions);
			criteria.setProjection(projection);
			result = criteria.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			LOG.error(
					"Error while findByCriteria Entities. M: " + e.getMessage()
							+ " C: " + e.getCause(), e);
		}
		return result;
	}

	protected Criteria createCriteria(Session session, Criterion... criterions) {
		Criteria criteria = session.createCriteria(getPersistentClass());
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		return criteria;
	}
}
