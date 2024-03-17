package com.jeeproj.company.project.entity;

import com.jeeproj.company.base.entity.BaseEntity;
import com.jeeproj.company.department.entity.Department;
import com.jeeproj.company.enums.Status;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Project extends BaseEntity {
    private String area;
    private String projectName;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;
}
