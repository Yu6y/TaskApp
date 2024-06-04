package com.example.TaskApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="user_tasks")
public class UserTasks {
    @EmbeddedId
    private UserTaskId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id")
    private Tasks tasks;
    @Column(name = "assigned_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime assignedAt;

    public UserTasks(UserTaskId id, User user, Tasks tasks, LocalDateTime assignedAt){
        this.id = id;
        this.user = user;
        this.tasks = tasks;
        this.assignedAt = assignedAt;
    }
    public UserTasks(User user, Tasks tasks, LocalDateTime assignedAt){
        this.id = new UserTaskId(user.getUserId(), tasks.getTaskId());
        this.user = user;
        this.tasks = tasks;
        this.assignedAt = assignedAt;
    }
}
