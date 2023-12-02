package com.fuad.aclDemo.user_info;

import com.fuad.aclDemo.role.RoleResponse;
import com.fuad.aclDemo.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    private Long id;
    private User user;
    private String lastName;
    private String fatherName;
    private String motherName;
    private String address;
    private String image;

}
