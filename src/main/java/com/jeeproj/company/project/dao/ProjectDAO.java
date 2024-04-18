package com.jeeproj.company.project.dao;

import com.jeeproj.company.base.dao.BaseDAO;
import com.jeeproj.company.project.entity.Project;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProjectDAO extends BaseDAO<Project> {
    public ProjectDAO() {
        super(Project.class);
    }

    @Override
    public List<Project> findAll() {
        return entityManager.createQuery("select p from Project p", Project.class)
                .getResultList();
    }

    public Optional<Project> findProjectById(Long id) {
        return entityManager.createQuery("select p from Project p " +
                        "where p.id = :id", Project.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst();
    }

    public List<Project> findProjectsByDepartmentName (String departmentName) {
        return entityManager.createQuery("select p from Project p " +
                        "where p.department.department_name = :departmentName", Project.class)
                .setParameter("departmentName", departmentName)
                .getResultList();
    }

    public List<Project> findProjectsByArea (String area) {
        return entityManager.createQuery("select p from Project p " +
                        "where p.area = :area", Project.class)
                .setParameter("area", area)
                .getResultList();
    }

    public void deleteProjectsByDepartment(Long id) {
        entityManager.createQuery("DELETE FROM Project p WHERE p.department.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    public Long getTotal() {
        TypedQuery<Long> query = entityManager
                .createQuery("select count(p.id) from Project p", Long.class);
        return query.getSingleResult();
    }

    public Long getTotalWithDepartmentId(Long departmentId) {
        TypedQuery<Long> query = entityManager
                .createQuery("select count(p.id) from Project " +
                        "p where p.department.id = :departmentId", Long.class)
                .setParameter("departmentId", departmentId);

        return query.getSingleResult();
    }
}
