package com.jeeproj.company.project.entity;

import com.jeeproj.company.assignment.entity.Assignment;
import com.jeeproj.company.base.entity.BaseEntity;
import com.jeeproj.company.department.entity.Department;
import com.jeeproj.company.base.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Project extends BaseEntity {
    private String projectName;
    private String area;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Set<Assignment> assignments;
}
