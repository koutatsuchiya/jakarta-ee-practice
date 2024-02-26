package com.jeeproj.company.department.dao;

import com.jeeproj.company.base.dao.BaseDAO;
import com.jeeproj.company.department.entity.Department;

import javax.ejb.Stateless;

@Stateless
public class DepartmentDAO extends BaseDAO<Department> {
    public DepartmentDAO() {
        super(Department.class);
    }

}
