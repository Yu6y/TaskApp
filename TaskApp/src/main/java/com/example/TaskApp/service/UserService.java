package com.example.TaskApp.service;

import com.example.TaskApp.model.User;
import com.example.TaskApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public String addUser(User user){
        if(userRepository.findById(user.getUserId()).isPresent())
            throw new RuntimeException("User already exist!");
        else{
            user.setCreatedAt(LocalDateTime.now());
            userRepository.save(user);
            return "User added successfully";
        }
    }

    public User getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        return user;
    }

    public String updateUser(User user){
        User userUpdate = userRepository.findById(user.getUserId()).orElseThrow(() -> new RuntimeException("User not found!"));
        userUpdate.setUsername(user.getUsername());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPasswordHash(user.getPasswordHash());

        userRepository.save(userUpdate);
        return "User updated successfully";
    }

    public String deleteUser(Long id){
        if(userRepository.findById(id).isEmpty())
            throw new RuntimeException("User not found!");
        else {
            userRepository.deleteById(id);
            return "User deleted successfully";
        }
    }

}
