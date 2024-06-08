package com.example.TaskApp.service;

import com.example.TaskApp.model.Tasks;
import com.example.TaskApp.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TasksRepository tasksRepository;

    public List<Tasks> getAllTasks(){
        return tasksRepository.findAll();
    }

    public String addTask(Tasks task){
        if(tasksRepository.findById(task.getTaskId()).isPresent())
            throw new RuntimeException("Task already exist!");
        else{
            task.setCreatedAt(LocalDateTime.now());
            tasksRepository.save(task);
            return "Task added successfully";
        }
    }

    public Tasks getTaskById(Long id){
        return tasksRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found!"));
    }

    public String updateTask(Tasks task){
        Tasks taskUpdate = tasksRepository.findById(task.getTaskId()).orElseThrow(() -> new RuntimeException("Task not found!"));
        taskUpdate.setTitle(task.getTitle());
        taskUpdate.setDescription(task.getDescription());
        taskUpdate.setDueDate(task.getDueDate());

        return "UserTask updated successfully";
    }

    public String deleteTask(Long id){
        if(tasksRepository.findById(id).isEmpty())
            throw new RuntimeException("Task not found!");
        else {
            tasksRepository.deleteById(id);
            return "Task deleted successfully";
        }
    }
}
