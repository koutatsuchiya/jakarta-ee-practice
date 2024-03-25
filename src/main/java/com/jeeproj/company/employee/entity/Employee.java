package com.jeeproj.company.employee.entity;

import com.jeeproj.company.assignment.entity.Assignment;
import com.jeeproj.company.base.entity.BaseEntity;
import com.jeeproj.company.department.entity.Department;
import com.jeeproj.company.relative.entity.Relative;
import lombok.*;
import com.jeeproj.company.base.enums.Gender;
import com.jeeproj.company.base.enums.Status;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    private Double salary;

    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Relative> relatives;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Assignment> assignments;

    @Transient
    public String getFullName() {
        return this.lastName + (this.middleName == null? " " : " " + this.middleName + " ") + this.firstName;
    }
}
