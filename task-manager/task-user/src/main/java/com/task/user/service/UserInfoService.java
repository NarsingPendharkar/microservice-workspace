package com.task.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.task.user.dao.UserInfoRepository;
import com.task.user.entity.UserInfo;
import com.task.user.exception.UserNotFoundException;

import jakarta.validation.Valid;

@Service
public class UserInfoService {

    
    private final UserInfoRepository userInfoRepository;
    
    public UserInfoService(UserInfoRepository userInfoRepository) {
    	this.userInfoRepository=userInfoRepository;
    }

    // ✅ Create new user
    public UserInfo createUser(@Valid UserInfo user) {
        return userInfoRepository.save(user);
    }

    // ✅ Get all users
    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }

    // ✅ Get user by ID
    public UserInfo getUserById(Long id) {
        return userInfoRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    // ✅ Update user
    public UserInfo updateUser(Long id, @Valid UserInfo updatedUser) {
        UserInfo existingUser = getUserById(id);

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAge(updatedUser.getAge());
        existingUser.setPassword(updatedUser.getPassword());

        return userInfoRepository.save(existingUser);
    }

    // ✅ Delete user
    public void deleteUser(Long id) {
        if (!userInfoRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        userInfoRepository.deleteById(id);
    }
}
