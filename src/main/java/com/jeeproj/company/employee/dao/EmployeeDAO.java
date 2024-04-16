package com.jeeproj.company.employee.dao;

import com.jeeproj.company.base.dao.BaseDAO;
import com.jeeproj.company.employee.entity.Employee;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;

@Stateless
public class EmployeeDAO extends BaseDAO<Employee> {
    public EmployeeDAO() {
        super(Employee.class);
    }

    public List<Employee> getEmployeesByDepartmentID (Long departmentID) {
        return entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.department.id = :departmentID", Employee.class)
                .setParameter("departmentID", departmentID)
                .getResultList();
    }

    public void deleteEmployeesByDepartment(Long id) {
        entityManager.createQuery("DELETE FROM Employee e WHERE e.department.id = :id")
                .setParameter("id", id).executeUpdate();
    }
}
