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

    public List<Employee> getEmployeeByDepartmentID (Long departmentID) {
        return entityManager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.department.id = :departmentID", Employee.class)
                .setParameter("departmentID", departmentID)
                .getResultList();
    }

    public Optional<Employee> findEmployeeById(Long id) {
        Employee employee = entityManager.createQuery("select e from Employee e " +
                        "where e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();

        return Optional.ofNullable(employee);
    }

    public void deleteEmployeesByDepartment(Long id) {
        entityManager.createQuery("DELETE FROM Employee e WHERE e.department.id = :id")
                .setParameter("id", id).executeUpdate();
    }
}
