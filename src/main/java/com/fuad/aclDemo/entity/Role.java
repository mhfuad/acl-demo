package com.fuad.aclDemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fuad.aclDemo.entity.Permission;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @ManyToMany(
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER)
    @JoinTable(
        name = "permission_role",
        joinColumns = @JoinColumn(
            name = "role_id",
            referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
            name = "permission_id",
            referencedColumnName = "id"
        )
    )
    private Set<Permission> permissions = new HashSet<>();
}
