package com.jeeproj.company.project.entity;

import com.jeeproj.company.base.entity.BaseEntity;
import com.jeeproj.company.department.entity.Department;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Builder
@Entity
public class Project extends BaseEntity {

    private String area;
    private String projectName;

    @ManyToOne
    private Department department;
}
