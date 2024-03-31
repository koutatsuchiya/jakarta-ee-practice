package com.jeeproj.company.user.dao;

import com.jeeproj.company.base.dao.BaseDAO;
import com.jeeproj.company.user.entity.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserDAO extends BaseDAO<User> {
    public UserDAO() {
        super(User.class);
    }

    public Optional<User> findByEmail(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> cq = cb.createQuery(entityClass);
        Root<User> root = cq.from(entityClass);
        cq.select(root).where(cb.equal(root.get("email"), email.trim()));

        TypedQuery<User> query = entityManager.createQuery(cq);
        List<User> result = query.getResultList();

        return result.stream().findFirst();
    }
}
