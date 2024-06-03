package com.example.TaskApp.service;

import com.example.TaskApp.model.User;
import com.example.TaskApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        if(userRepository.findById(user.getUserId()).isPresent())
            throw new RuntimeException("User already exist!");
        else{
            userRepository.save(user);
            return user;
        }
    }

    public User getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        return user;
    }

    public User updateUser(User user){
        User userUpdate = userRepository.findById(user.getUserId()).orElseThrow(() -> new RuntimeException("User not found!"));
        userUpdate.setUsername(user.getUsername());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPasswordHash(user.getPasswordHash());

        userRepository.save(userUpdate);
        return userUpdate;
    }

    public String deleteUser(Long id){
        if(userRepository.findById(id).isEmpty())
            throw new RuntimeException("User not found!");
        else {
            userRepository.deleteById(id);
            return "Deleted user with id:" + id;
        }
    }

}
