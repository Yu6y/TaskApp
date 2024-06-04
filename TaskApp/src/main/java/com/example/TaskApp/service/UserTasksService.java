package com.example.TaskApp.service;

import com.example.TaskApp.model.User;
import com.example.TaskApp.model.UserTaskId;
import com.example.TaskApp.model.UserTasks;
import com.example.TaskApp.repository.UserRepository;
import com.example.TaskApp.repository.UserTasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTasksService {

    @Autowired
    private UserTasksRepository userTasksRepository;
    @Autowired
    private UserRepository userRepository;

    public List<UserTasks> getAllUserTasks() {
        return userTasksRepository.findAll();
    }

    public UserTasks getUserTaskById(UserTaskId id) {
        return userTasksRepository.findById(id).orElseThrow(() -> new RuntimeException("User task not found!"));
    }

    public UserTasks addUserTask(UserTasks userTask) {
        if (userTasksRepository.findByUserAndTasks(userTask.getUser(), userTask.getTasks()).isPresent()) {
            throw new RuntimeException("User task already exists!");
        } else {
            userTasksRepository.save(userTask);
            return userTask;
        }
    }

    public String deleteUserTask(Long userId, Long taskId) {
        UserTaskId id = new UserTaskId(userId, taskId);
        if (!userTasksRepository.existsById(id)) {
            throw new RuntimeException("User task not found!");
        } else {
            userTasksRepository.deleteById(id);
            return "Deleted UserTask with id: " + userId + ',' + taskId;
        }
    }

    public List<UserTasks> getUserTaskByUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        return userTasksRepository.findAllByUser(user);
    }
}
