package com.jeeproj.company.department.entity;

import com.jeeproj.company.base.entity.BaseEntity;
import com.jeeproj.company.base.enums.Status;
import lombok.*;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Department extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String departmentName;

    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private Status status;
}
