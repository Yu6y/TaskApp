package com.example.TaskApp.service;

import com.example.TaskApp.dto.LoginDto;
import com.example.TaskApp.model.User;
import com.example.TaskApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
}
