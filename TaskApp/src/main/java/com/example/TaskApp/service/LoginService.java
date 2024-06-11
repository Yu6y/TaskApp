package com.example.TaskApp.service;

import com.example.TaskApp.dto.LoginDto;
import com.example.TaskApp.model.User;
import com.example.TaskApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

   public HashMap<String, Object> loginUser(LoginDto credentials){
       User user = userRepository.findByUsername(credentials.getLogin()).orElseThrow(() -> new RuntimeException("Credentials incorrect!"));
       if(credentials.getPassword().equals(user.getPasswordHash())){
           HashMap<String, Object> response = new HashMap<>();
           response.put("id", user.getUserId());
           response.put("login", user.getUsername());
           return response;
       }

       else
           throw new RuntimeException("Credentials incorrect!");
   }

   public HashMap<String, Object> registerUser(LoginDto loginDto){

       Optional<User> user = userRepository.findByUsername(loginDto.getLogin());
       if(user.isEmpty()) {
           User userAdd = new User();
           //userAdd.setUserId((long) 1);
           userAdd.setUsername(loginDto.getLogin());
           userAdd.setPasswordHash(loginDto.getPassword());
           userAdd.setCreatedAt(LocalDateTime.now());

           userRepository.save(userAdd);
           HashMap<String, Object> response = new HashMap<>();
           response.put("userId", userAdd.getUserId());
           response.put("login", userAdd.getUsername());
           return response;
       }
       else
           throw new RuntimeException("User already exists!");


   }
}
