package com.jeeproj.company.department.dao;

import com.jeeproj.company.base.dao.BaseDAO;
import com.jeeproj.company.department.entity.Department;

import javax.ejb.Stateless;
import java.util.Optional;

@Stateless
public class DepartmentDAO extends BaseDAO<Department> {
    public DepartmentDAO() {
        super(Department.class);
    }

    public Optional<Department> findDepartmentByName(String departmentName) {
        Department department = entityManager.createQuery("SELECT d FROM Department d " +
                "WHERE d.name = :departmentName", Department.class)
                .setParameter("departmentName", departmentName)
                .getSingleResult();
        return Optional.ofNullable(department);
    }
}
