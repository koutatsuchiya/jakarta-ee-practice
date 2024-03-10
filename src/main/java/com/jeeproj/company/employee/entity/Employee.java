package com.jeeproj.company.employee.entity;

import com.jeeproj.company.base.entity.BaseEntity;
import com.jeeproj.company.department.entity.Department;
import lombok.*;
import com.jeeproj.company.enums.Gender;
import com.jeeproj.company.enums.Status;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Employee extends BaseEntity {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String middleName;
    private Integer salary;

    @JsonbDateFormat("yyyy-MM-dd")
    private String dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @Transient
    public String getFullName() {
        return this.lastName + (this.middleName == null? " " : " " + this.middleName + " ") + this.firstName;
    }
}
