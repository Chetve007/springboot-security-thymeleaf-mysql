package com.alex.springBoot.springBootApp.repositories;

import com.alex.springBoot.springBootApp.entities.Task;
import com.alex.springBoot.springBootApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);
}
