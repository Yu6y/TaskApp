package com.example.TaskApp.controller;

import com.example.TaskApp.model.Tasks;
import com.example.TaskApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/task")
public class TasksController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public ResponseEntity<?> getTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id){
        HashMap<String, Object> response = new HashMap<>();
        try{
            response.put("success", taskService.getTaskById(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/addTask")
    public ResponseEntity<?> addTask(@RequestBody Tasks task){
        HashMap<String, Object> response = new HashMap<>();
        try{
            response.put("success", taskService.addTask(task));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/updateTask")
    public ResponseEntity<?> updateTask(@RequestBody Tasks task){
        HashMap<String, Object> response = new HashMap<>();
        try{
            response.put("success", taskService.updateTask(task));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        HashMap<String, Object> response = new HashMap<>();
        try{
            response.put("success", taskService.deleteTask(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}
