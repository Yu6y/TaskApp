package com.example.TaskApp.repository;

import com.example.TaskApp.model.Tasks;
import com.example.TaskApp.model.User;
import com.example.TaskApp.model.UserTaskId;
import com.example.TaskApp.model.UserTasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTasksRepository extends JpaRepository<UserTasks, UserTaskId> {
    Optional<UserTasks> findByUserAndTasks(User user, Tasks tasks);
}