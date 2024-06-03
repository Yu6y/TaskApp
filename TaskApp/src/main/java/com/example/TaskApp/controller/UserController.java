package com.example.TaskApp.controller;

import com.example.TaskApp.model.User;
import com.example.TaskApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        HashMap<String, Object> response = new HashMap<>();
        try{
            response.put("user", userService.getUserById(id));
            return new ResponseEntity<>(response , HttpStatus.OK);
        }catch(Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addProduct(@RequestBody User user){
        HashMap<String, Object> response = new HashMap<>();
        try{
            response.put("users", userService.addUser(user));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        HashMap<String, Object> response = new HashMap<>();
        try{
            response.put("user", userService.updateUser(user));
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }catch(Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        HashMap<String, Object> response = new HashMap<>();
        try{
            response.put("success", userService.deleteUser(id));
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }catch(Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}
