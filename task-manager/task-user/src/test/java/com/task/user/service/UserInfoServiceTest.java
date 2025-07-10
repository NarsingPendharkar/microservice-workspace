package com.task.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.task.user.dao.UserInfoRepository;
import com.task.user.entity.UserInfo;
import com.task.user.exception.UserNotFoundException;

import jakarta.validation.Valid;


@ExtendWith(MockitoExtension.class)
public class UserInfoServiceTest {

	@Mock
	private UserInfoRepository userInfoRepository;

	@InjectMocks
	private UserInfoService userInfoService;
    
    private UserInfo user;
    
    @BeforeEach
    public void setUp() {
        user = new UserInfo();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setAge(25);
        user.setPassword("password123");
    }
    // ✅ Create new user
    @Test
    public void testCreateUser() {
        when(userInfoRepository.save(user)).thenReturn(user);
        UserInfo result = userInfoService.createUser(user);
        assertEquals(user, result);
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
