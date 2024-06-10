package com.example.TaskApp.controller;

import com.example.TaskApp.dto.LoginDto;
import com.example.TaskApp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@RestController
@RequestMapping("api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @PostMapping()
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto){
        HashMap<String, Object> response = new HashMap<>();
        try{
            response = loginService.loginUser(loginDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody LoginDto loginDto){
        HashMap<String, Object> response = new HashMap<>();
        try{
            response.put("success", loginService.registerUser(loginDto));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

}
