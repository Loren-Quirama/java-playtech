package com.useradministration.user.repository;

import com.useradministration.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity>findByMobilePhone(String mobile_phone);
}
