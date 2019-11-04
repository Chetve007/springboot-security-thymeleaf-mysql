package com.alex.springBoot.springBootApp.services;

import com.alex.springBoot.springBootApp.entities.Task;
import com.alex.springBoot.springBootApp.entities.User;
import com.alex.springBoot.springBootApp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public void addTask(Task task, User user) {
        task.setUser(user);
        taskRepository.save(task);
    }

    public List<Task> findUserTask(User user) {
        return taskRepository.findByUser(user);
    }
}
