package com.bahadirakin.dao;

import com.bahadirakin.entities.ShortMessage;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by bhdrkn on 14/11/14.
 */
@Transactional
public class SmDaoImpl implements SmDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public void persist(ShortMessage shortMessage) {
        entityManager.persist(shortMessage);

    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
