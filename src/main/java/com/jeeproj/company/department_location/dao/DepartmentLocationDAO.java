package com.jeeproj.company.department_location.dao;

import com.jeeproj.company.base.dao.BaseDAO;
import com.jeeproj.company.department_location.entity.DepartmentLocation;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;

@Stateless
public class DepartmentLocationDAO extends BaseDAO<DepartmentLocation> {
    public DepartmentLocationDAO() {
        super(DepartmentLocation.class);
    }

    public List<DepartmentLocation> findLocationsByDepartmentId(Long departmentId) {
        return entityManager.createQuery("select dl from DepartmentLocation dl " +
                        "where dl.department.id = :departmentId", DepartmentLocation.class)
                .setParameter("departmentId", departmentId)
                .getResultList();
    }

    public Optional<DepartmentLocation> findDepartmentLocation(String location, Long departmentId) {
        DepartmentLocation departmentLocation = entityManager.createQuery("select dl from DepartmentLocation dl " +
                        "where lower(dl.location) = :location " +
                        "and dl.department.id = :departmentId", DepartmentLocation.class)
                .setParameter("location", location)
                .setParameter("departmentId", departmentId)
                .getSingleResult();

        return Optional.ofNullable(departmentLocation);
    }

    public void deleteLocationsByDepartment(Long id) {
        entityManager.createQuery("DELETE FROM DepartmentLocation dl WHERE dl.department.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    public Long getTotal(Long departmentId) {
        return entityManager.createQuery("select count(dl.id) from DepartmentLocation dl " +
                        "where dl.department.id = :departmentId", Long.class)
                .setParameter("departmentId", departmentId)
                .getSingleResult();
    }
}
