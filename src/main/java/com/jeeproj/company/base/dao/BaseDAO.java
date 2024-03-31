package com.jeeproj.company.base.dao;

import com.jeeproj.company.base.entity.BaseEntity;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseDAO<T extends BaseEntity> {
    @PersistenceContext
    protected EntityManager entityManager;
    protected final Class<T> entityClass;

    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<T> q = cb.createQuery(entityClass);
        q.from(entityClass);

        return entityManager.createQuery(q).getResultList();
    }

    public T add(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T update(T entity) {
        entityManager.merge(entity);
        return entity;
    }

    public Optional<T> findById(long id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }
}
