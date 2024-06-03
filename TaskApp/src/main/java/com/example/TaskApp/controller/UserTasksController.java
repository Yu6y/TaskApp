package com.example.TaskApp.controller;

import com.example.TaskApp.model.UserTaskId;
import com.example.TaskApp.model.UserTasks;
import com.example.TaskApp.service.UserTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/userTask")
public class UserTasksController {

    @Autowired
    private UserTasksService userTasksService;

    @GetMapping()
    public ResponseEntity<?> getUserTasks() {
        return new ResponseEntity<>(userTasksService.getAllUserTasks(), HttpStatus.OK);
    }

    @GetMapping("/{userId}/{taskId}")
    public ResponseEntity<?> getUserTaskById(@PathVariable Long userId, @PathVariable Long taskId) {
        HashMap<String, Object> response = new HashMap<>();
        UserTaskId id = new UserTaskId(userId, taskId);
        try {
            response.put("userTask", userTasksService.getUserTaskById(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/addUserTask")
    public ResponseEntity<?> addUserTask(@RequestBody UserTasks userTask) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            response.put("userTask", userTasksService.addUserTask(userTask));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteUserTask/{userId}/{taskId}")
    public ResponseEntity<?> deleteUserTask(@PathVariable Long userId, @PathVariable Long taskId) {
        HashMap<String, Object> response = new HashMap<>();

        try {
            response.put("success", userTasksService.deleteUserTask(userId, taskId));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}
