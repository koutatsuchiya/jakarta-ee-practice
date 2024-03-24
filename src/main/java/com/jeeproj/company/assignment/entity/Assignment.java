package com.jeeproj.company.assignment.entity;

import com.jeeproj.company.base.entity.BaseEntity;
import com.jeeproj.company.employee.entity.Employee;
import com.jeeproj.company.project.entity.Project;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UQ_eid_pid", columnNames = { "employee_id", "project_id" }) })
public class Assignment extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private int numberOfHour;
}
