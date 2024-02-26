package com.jeeproj.company.assignment.dao;

import com.jeeproj.company.assignment.entity.Assignment;
import com.jeeproj.company.base.dao.BaseDAO;

import javax.ejb.Stateless;

@Stateless
public class AssignmentDao extends BaseDAO<Assignment> {

    public AssignmentDao() {
        super(Assignment.class);
    }

//    public List<Assignment> getAssignmentByEmpId(Long empId) {
//        entityManager.createQuery("SELECT a FROM Assignment a WHERE a.employee.id = :")
//    }
}
