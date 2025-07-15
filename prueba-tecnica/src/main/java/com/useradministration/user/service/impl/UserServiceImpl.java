package com.useradministration.user.service.impl;

import com.useradministration.user.entity.UserEntity;
import com.useradministration.user.repository.UserRepository;
import com.useradministration.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;

    @Override
    public Object createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity loginOrCreate(String mobile_phone, String password) {
        return userRepository.findByMobilePhone(mobile_phone)
                .orElseGet(() -> {
                    UserEntity newUser = new UserEntity();
                    newUser.setMobilePhone(mobile_phone);
                    newUser.setPassword(password);
                    return userRepository.save(newUser);
                });
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirst_name(updatedUser.getFirst_name());
                    existingUser.setLast_name(updatedUser.getLast_name());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setAddress(updatedUser.getAddress());
                    existingUser.setMobilePhone(updatedUser.getMobilePhone());
                    existingUser.setPassword(updatedUser.getPassword());
                    existingUser.setDate_birth(updatedUser.getDate_birth());
                    return userRepository.save(existingUser);
                })
                .orElse(null);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                })
                .orElse(false);
    }

}
