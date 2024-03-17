package com.jeeproj.company.relative.dto;

import com.jeeproj.company.employee.entity.Employee;
import com.jeeproj.company.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelativeDTO {
    private Long id;
    private String fullName;
    private Gender gender;
    private String phoneNumber;
    private String relationship;

    public RelativeDTO(Long id, String fullName, Gender gender, String phoneNumber, String relationship) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.relationship = relationship;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Employee employee;
}
