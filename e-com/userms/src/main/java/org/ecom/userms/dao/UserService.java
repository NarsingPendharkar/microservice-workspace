package org.ecom.userms.dao;


import java.util.List;
import java.util.Optional;

import org.ecom.userms.model.User;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);
}
