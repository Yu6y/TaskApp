package com.example.TaskApp.controller;

import com.example.TaskApp.dto.UserTaskAddDto;
import com.example.TaskApp.dto.UserTaskDeleteDto;
import com.example.TaskApp.dto.UserTaskGetByIdDto;
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
    @GetMapping("/getUserTasks/v1")
    public ResponseEntity<?> getUserTasks(@RequestBody UserTaskGetByIdDto userTask) {
        return new ResponseEntity<>(userTasksService.getUserTaskByUser(userTask), HttpStatus.OK);
    }


    @PostMapping("/addUserTask/v1")
    public ResponseEntity<?> addUserTask(@RequestBody UserTaskAddDto userTask) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            response.put("success", userTasksService.addUserTask(userTask));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteUserTask/v1")
    public ResponseEntity<?> deleteUserTask(@RequestBody UserTaskDeleteDto userTaskDeleteDto) {
        HashMap<String, Object> response = new HashMap<>();

        try {
            response.put("success", userTasksService.deleteUserTask(userTaskDeleteDto));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}
