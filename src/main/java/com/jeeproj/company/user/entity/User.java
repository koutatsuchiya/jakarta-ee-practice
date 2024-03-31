package com.jeeproj.company.user.entity;

import com.jeeproj.company.base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "display_name")
    private String displayName;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
