package com.fuad.aclDemo.user_info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fuad.aclDemo.role.Role;
import com.fuad.aclDemo.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String fatherName;

    private String motherName;

    @Lob
    @Column(length = 5000)
    private String address;

    private String image;
}
