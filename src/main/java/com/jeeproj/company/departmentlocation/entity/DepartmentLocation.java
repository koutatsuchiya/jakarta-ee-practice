package com.jeeproj.company.departmentlocation.entity;

import com.jeeproj.company.base.entity.BaseEntity;
import com.jeeproj.company.department.entity.Department;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UQ_dept_loc", columnNames = { "location", "department_id" }) })
public class DepartmentLocation extends BaseEntity {
    private String location;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
