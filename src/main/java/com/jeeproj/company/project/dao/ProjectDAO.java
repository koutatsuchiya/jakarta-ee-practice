package com.jeeproj.company.project.dao;

import com.jeeproj.company.base.dao.BaseDAO;
import com.jeeproj.company.project.entity.Project;

public class ProjectDAO extends BaseDAO<Project> {
    public ProjectDAO() {
        super(Project.class);
    }
}
