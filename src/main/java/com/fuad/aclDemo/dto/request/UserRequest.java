package com.fuad.aclDemo.dto.request;

import com.fuad.aclDemo.enums.UserTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotEmpty
    @NotNull(message = "First name is required")
    private String firstName;
    @NotEmpty
    @NotNull(message = "Last name is required")
    private String lastName;
    @NotEmpty
    @NotNull(message = "Email name is required")
    private String email;
    @NotEmpty
    @NotNull(message = "Password is required")
    private String password;
    @NotEmpty
    @NotNull(message = "Phone number name is required")
    private String phoneNumber;
}
