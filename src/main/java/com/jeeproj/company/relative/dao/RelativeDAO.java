package com.jeeproj.company.relative.dao;

import com.jeeproj.company.base.dao.BaseDAO;
import com.jeeproj.company.department.entity.Department;
import com.jeeproj.company.employee.entity.Employee;
import com.jeeproj.company.relative.dto.RelativeDTO;
import com.jeeproj.company.relative.entity.Relative;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class RelativeDAO extends BaseDAO<Relative> {
    public RelativeDAO() {
        super(Relative.class);
    }

    public List<Relative> findRelativesByEmployeeId(Long employeeId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Relative> query = cb.createQuery(Relative.class);
        Root<Relative> relativeRoot = query.from(Relative.class);
        Fetch<Relative, Employee> employeeFetch = relativeRoot.fetch("employee");

        Fetch<Employee, Department> relativeEmployeeFetch = employeeFetch.fetch("department");

        query.select(relativeRoot);
        query.where(cb.equal(relativeRoot.get("employee").get("id"), employeeId));

        return entityManager.createQuery(query).getResultList();
    }

    public List<RelativeDTO> findRelativesByDepartment(Long deptId) {
        return entityManager.createNamedQuery("Relative.findRelativesByDepartment", RelativeDTO.class)
                .setParameter("departmentId", deptId).getResultList();
    }

    public void deleteRelativesByEmployees(Long id) {
        entityManager.createQuery("DELETE FROM Relative r WHERE r.employee.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    public Long getTotal(Long employeeId) {
        Query query = entityManager.createQuery("select count(r.id) from Relative r where r.employee.id = :employeeId");
        query.setParameter("employeeId", employeeId);
        return (Long)query.getSingleResult();
    }
}
