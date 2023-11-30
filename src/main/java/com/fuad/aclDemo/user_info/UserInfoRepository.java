package com.fuad.aclDemo.user_info;

import com.fuad.aclDemo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

}
