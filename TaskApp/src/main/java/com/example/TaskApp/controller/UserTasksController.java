package com.example.TaskApp.controller;

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
    @GetMapping("/getUserTasks/{id}")
    public ResponseEntity<?> getUserTasks(@PathVariable Long id) {
        return new ResponseEntity<>(userTasksService.getUserTaskByUser(id), HttpStatus.OK);
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

    @DeleteMapping("/deleteUserTask/{id}")
    public ResponseEntity<?> deleteUserTask(@PathVariable Long id) {
        HashMap<String, Object> response = new HashMap<>();

        try {
            response.put("success", userTasksService.deleteUserTask(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}
