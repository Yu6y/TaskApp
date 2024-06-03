package com.example.TaskApp.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserTaskId implements Serializable {

    private Long userId;
    private Long taskId;


    public UserTaskId() {}

    public UserTaskId(Long userId, Long taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTaskId that = (UserTaskId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, taskId);
    }

}