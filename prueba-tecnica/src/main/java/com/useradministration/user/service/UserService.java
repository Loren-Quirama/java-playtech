package com.useradministration.user.service;

import com.useradministration.user.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Object createUser(UserEntity user);

    UserEntity loginOrCreate(String mobile_phone, String password);

    List<UserEntity> getAllUsers();

    Optional<UserEntity> getUserById(Long id);

    UserEntity updateUser(Long id, UserEntity updatedUser);

    boolean deleteUser(Long id);
}
