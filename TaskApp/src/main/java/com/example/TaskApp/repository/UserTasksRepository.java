package com.example.TaskApp.repository;

import com.example.TaskApp.model.Tasks;
import com.example.TaskApp.model.User;
import com.example.TaskApp.model.UserTasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserTasksRepository extends JpaRepository<UserTasks, Long> {
    Optional<UserTasks> findByUserAndTasks(User user, Tasks tasks);
    List<UserTasks> findAllByUser(User user);
}
