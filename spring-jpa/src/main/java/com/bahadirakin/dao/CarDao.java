package com.bahadirakin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bahadirakin.model.Car;

@Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class, value = "transactionManager")
public class CarDao implements ICarDao {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "entityManagerFactory")
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/* (non-Javadoc)
	 * @see com.bahadirakin.dao.ICarDao#detach(java.lang.Object)
	 */
	@Override
	public void detach(Object arg0) {
		entityManager.detach(arg0);
	}

	/* (non-Javadoc)
	 * @see com.bahadirakin.dao.ICarDao#findAll()
	 */
	@Override
	public List<Car> findAll() {
		System.out.println(entityManager);
		return entityManager.createQuery("SELECT car FROM Car car").getResultList();
	}

	/* (non-Javadoc)
	 * @see com.bahadirakin.dao.ICarDao#merge(com.bahadirakin.model.Car)
	 */
	@Override
	public Car merge(Car arg0) {
		return entityManager.merge(arg0);
	}

	/* (non-Javadoc)
	 * @see com.bahadirakin.dao.ICarDao#persist(com.bahadirakin.model.Car)
	 */
	@Override
	public void persist(Car arg0) {
		entityManager.persist(arg0);
	}

}
