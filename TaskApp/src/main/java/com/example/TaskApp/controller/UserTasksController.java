package com.example.TaskApp.controller;

import com.example.TaskApp.dto.*;
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

    @GetMapping("/getUserTasks/v2/{id}")
    public ResponseEntity<?> getUserTasks(@PathVariable Long id) {
        return new ResponseEntity<>(userTasksService.getUserTaskByUser(id), HttpStatus.OK);
    }

    @PostMapping("/addUserTask/v2/{id}")
    public ResponseEntity<?> addUserTask(@PathVariable Long id, @RequestBody UserTaskAddDtoV2 userTask) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            response.put("success", userTasksService.addUserTask(id, userTask));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteUserTask/v2/{id}")
    public ResponseEntity<?> deleteUserTask(@PathVariable Long id, @RequestBody UserTaskDeleteDtoV2 userTaskDeleteDto) {
        HashMap<String, Object> response = new HashMap<>();

        try {
            response.put("success", userTasksService.deleteUserTask(id, userTaskDeleteDto));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}
