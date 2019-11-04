package com.alex.springBoot.springBootApp.services;

import com.alex.springBoot.springBootApp.entities.Role;
import com.alex.springBoot.springBootApp.entities.User;
import com.alex.springBoot.springBootApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        createUserOrAdmin(user, "USER");
    }

    public void createAdmin(User user) {
        createUserOrAdmin(user, "ADMIN");
    }

    private void createUserOrAdmin(User user, String role) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role(role);
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }

    public User findOne(String email) {
        try {
            return userRepository.findById(email).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean isUserPresent(String email) {
            User user = findOne(email);
            return user != null;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findByName(String name) {
        return userRepository.findByNameLike("%" + name + "%");
    }
}
