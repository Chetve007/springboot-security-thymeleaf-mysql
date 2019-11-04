package com.alex.springBoot.springBootApp.repositories;

import com.alex.springBoot.springBootApp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
