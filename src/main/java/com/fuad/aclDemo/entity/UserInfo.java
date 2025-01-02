package com.fuad.aclDemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fuad.aclDemo.entity.User;
import jakarta.persistence.*;
import lombok.*;

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
