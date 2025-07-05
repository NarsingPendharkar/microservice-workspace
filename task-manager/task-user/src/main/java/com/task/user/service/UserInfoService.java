package com.task.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.user.dao.UserInfoRepository;
import com.task.user.entity.UserInfo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    // ✅ Create new user
    public UserInfo createUser(@Valid UserInfo user) {
        return userInfoRepository.save(user);
    }

    // ✅ Get all users
    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }

    // ✅ Get user by ID
    public UserInfo getUserById(UUID id) {
        return userInfoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
    }

    // ✅ Update user
    public UserInfo updateUser(UUID id, @Valid UserInfo updatedUser) {
        UserInfo existingUser = getUserById(id);

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAge(updatedUser.getAge());
        existingUser.setPassword(updatedUser.getPassword());

        return userInfoRepository.save(existingUser);
    }

    // ✅ Delete user
    public void deleteUser(UUID id) {
        if (!userInfoRepository.existsById(id)) {
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }
        userInfoRepository.deleteById(id);
    }
}
