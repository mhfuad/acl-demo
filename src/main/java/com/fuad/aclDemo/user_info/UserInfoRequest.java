package com.fuad.aclDemo.user_info;

import com.fuad.aclDemo.user.User;
import com.fuad.aclDemo.user.UserTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoRequest {

    private User user;
    private String fatherName;
    private String motherName;
    private String address;
}
