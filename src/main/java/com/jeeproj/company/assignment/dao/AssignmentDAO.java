package com.jeeproj.company.assignment.dao;

import com.jeeproj.company.assignment.entity.Assignment;
import com.jeeproj.company.base.dao.BaseDAO;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Stateless
public class AssignmentDAO extends BaseDAO<Assignment> {
    public AssignmentDAO() {
        super(Assignment.class);
    }

    public List<Assignment> findAssignmentsByProjectId(Long projectId) {
        Query query = entityManager.createQuery("select a from Assignment a where a.project.id = :projectId", Assignment.class);
        query.setParameter("projectId", projectId);

        return query.getResultList();
    }

    public Optional<Assignment> findAssignmentByEmployeeIdAndProjectId(Long employeeId, Long projectId) {
        Assignment assignment = entityManager
                .createQuery("select a from Assignment a where a.project.id = :projectId and a.employee.id = :employeeId", Assignment.class)
                .setParameter("projectId", projectId)
                .setParameter("employeeId", employeeId)
                .getResultList().stream().findFirst().orElse(null);

        return Optional.ofNullable(assignment);
    }

    public Long findTotalCountByProjectId(Long projectId) {
        Query query = entityManager.createQuery("select count(a.id) from Assignment a where a.project.id = :projectId");
        query.setParameter("projectId", projectId);
        return (Long)query.getSingleResult();
    }

    public Long findTotalCountByEmployeeId(Long employeeId) {
        Query query = entityManager.createQuery("select count(a.id) from Assignment a where a.employee.id = :employeeId");
        query.setParameter("employeeId", employeeId);
        return (Long)query.getSingleResult();
    }
}
