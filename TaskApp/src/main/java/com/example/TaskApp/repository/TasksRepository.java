package com.example.TaskApp.repository;

import com.example.TaskApp.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
}
