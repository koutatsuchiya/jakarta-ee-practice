package com.jeeproj.company.relative.entity;

import com.jeeproj.company.base.entity.BaseEntity;
import com.jeeproj.company.employee.entity.Employee;
import com.jeeproj.company.base.enums.Gender;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "relatives")
@NamedEntityGraphs(
        @NamedEntityGraph(name = "graph.Relative.emp",
                attributeNodes = @NamedAttributeNode(value = "employee", subgraph = "graph.Employee.dept"),
                subgraphs = {
                        @NamedSubgraph(name = "graph.Employee.dept",
                                attributeNodes = @NamedAttributeNode(value = "department")
                        )
                }
        )
)
@NamedQueries({
        @NamedQuery(
                name = "Relative.findRelativesByDepartment",
                query = "SELECT " +
                        "new com.jeeproj.company.relative.dto.RelativeDTO(" +
                        "r.id, r.fullName, r.gender,r.phoneNumber,r.relationship) " +
                        "FROM Relative r " +
                        "JOIN FETCH Employee e ON e.id = r.employee.id " +
                        "JOIN FETCH Assignment a ON e.id = a.employee.id " +
                        "JOIN FETCH Project p ON p.id = a.project.id " +
                        "JOIN FETCH Department d ON d.id = p.department.id " +
                        "WHERE d.id = :departmentId"
        ),
        @NamedQuery(
                name = "Relative.findRelativesByDepartmentGraph",
                query = "SELECT r " +
                        "FROM Relative r " +
                        "JOIN Employee e ON e.id = r.employee.id " +
                        "JOIN Assignment a ON e.id = a.employee.id " +
                        "JOIN Project p ON p.id = a.project.id " +
                        "JOIN Department d ON d.id = p.department.id " +
                        "WHERE d.id = :departmentId"
        )
})
public class Relative extends BaseEntity {
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;
    private String relationship;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
