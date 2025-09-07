/*
 * 
 */
package org.ecom.userms.service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.ecom.userms.dao.UserRepository;
import org.ecom.userms.exception.UserAlreadyPresentException;
import org.ecom.userms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService,UserDetailsService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
    	if(userRepository.existsByEmail(user.getEmail())) {
    		throw new UserAlreadyPresentException("User already present !");
    	}
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User systemUser = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found !"));
        System.out.println(systemUser);
        return new org.springframework.security.core.userdetails.User(
            systemUser.getEmail(),
            systemUser.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(systemUser.getRole()))
        );
    }

}
