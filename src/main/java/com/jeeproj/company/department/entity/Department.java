package com.jeeproj.company.department.entity;

import com.jeeproj.company.base.entity.BaseEntity;
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
    @Column(nullable = false)
    private String name;

    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate startDate;

}
