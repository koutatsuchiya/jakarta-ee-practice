package com.jeeproj.company.assignment.dao;

import com.jeeproj.company.assignment.entity.Assignment;
import com.jeeproj.company.base.dao.BaseDAO;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;

@Stateless
public class AssignmentDAO extends BaseDAO<Assignment> {
    public AssignmentDAO() {
        super(Assignment.class);
    }

    public List<Assignment> findAssignmentsByProjectId(Long projectId) {
        return entityManager.createQuery("select a from Assignment a " +
                "where a.project.id = :projectId", Assignment.class)
                .setParameter("projectId", projectId)
                .getResultList();
    }

    public Optional<Assignment> findAssignmentByProjectAndEmployee(Long projectId, Long employeeId) {
        return entityManager.createQuery("select a from Assignment a " +
                        "where a.project.id = :projectId and a.employee.id = :employeeId", Assignment.class)
                .setParameter("projectId", projectId)
                .setParameter("employeeId", employeeId)
                .getResultList().stream().findFirst();
    }

    public void deleteAssignmentsByProject(Long projectId) {
        entityManager.createQuery("DELETE FROM Assignment a WHERE a.project.id = :id")
                .setParameter("id", projectId).executeUpdate();
    }

    public void deleteAssignmentsByEmployee(Long employeeId) {
        entityManager.createQuery("DELETE FROM Assignment a WHERE a.employee.id = :id")
                .setParameter("id", employeeId).executeUpdate();
    }

    public Long findTotalCountByProjectId(Long projectId) {
        return entityManager.createQuery("select count(a.id) from Assignment a " +
                        "where a.project.id = :projectId", Long.class)
                .setParameter("projectId", projectId)
                .getSingleResult();
    }

    public Long findTotalCountByEmployeeId(Long employeeId) {
        return entityManager.createQuery("select count(a.id) from Assignment a " +
                        "where a.employee.id = :employeeId", Long.class)
                .setParameter("employeeId", employeeId)
                .getSingleResult();
    }
}
