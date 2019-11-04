package com.alex.springBoot.springBootApp.repositories;

import com.alex.springBoot.springBootApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByNameLike(String name);
}
