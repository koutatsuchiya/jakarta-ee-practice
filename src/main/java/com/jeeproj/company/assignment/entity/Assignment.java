package com.jeeproj.company.assignment.entity;

import com.jeeproj.company.base.entity.BaseEntity;
import com.jeeproj.company.employee.entity.Employee;
import com.jeeproj.company.project.entity.Project;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Assignment extends BaseEntity {

    private int numberOfHours;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Project project;
}
