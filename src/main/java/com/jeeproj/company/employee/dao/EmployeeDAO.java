package com.jeeproj.company.employee.dao;

import com.jeeproj.company.base.dao.BaseDAO;
import com.jeeproj.company.employee.entity.Employee;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class EmployeeDAO extends BaseDAO<Employee> {
    public EmployeeDAO() {
        super(Employee.class);
    }

    public List<Employee> getEmployeeByDepartmentID (Long departmentID) {
        return entityManager.createQuery("SELECT e " +
                "FROM Employee e " +
                "WHERE e.department.id = :departmentID", Employee.class)
                .setParameter("departmentID", departmentID)
                .getResultList();
    }

}
