    package com.example.TaskApp.service;

    import com.example.TaskApp.model.Tasks;
    import com.example.TaskApp.model.User;
    import com.example.TaskApp.model.UserTasks;
    import com.example.TaskApp.repository.TasksRepository;
    import com.example.TaskApp.repository.UserRepository;
    import com.example.TaskApp.repository.UserTasksRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;

    @Service
    public class UserTasksService {

        @Autowired
        private UserTasksRepository userTasksRepository;
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private TasksRepository tasksRepository;
        @Autowired
        private TaskService taskService;

        public List<UserTasks> getAllUserTasks() {
            return userTasksRepository.findAll();
        }


        public String addUserTask(UserTasks userTask) {

            if(userRepository.findById(userTask.getUser().getUserId()).isEmpty())
                throw new RuntimeException("User not found!");
            if (userTasksRepository.findByUserAndTasks(userTask.getUser(), userTask.getTasks()).isPresent()) {
                throw new RuntimeException("User task already exists!");
            }
            if(tasksRepository.findById(userTask.getTasks().getTaskId()).isEmpty()) {
                throw new RuntimeException("Task not found!");
            }
            if(tasksRepository.findById(userTask.getTasks().getTaskId()).isEmpty()){
                taskService.addTask(userTask.getTasks());
                userTask.setAssignedAt(LocalDateTime.now());
                userTasksRepository.save(userTask);
            }
            return "UserTask added successfully";
        }

        public String deleteUserTask(Long userTaskId) {

            if (!userTasksRepository.existsById(userTaskId)) {
                throw new RuntimeException("User task not found!");
            } else {
                userTasksRepository.deleteById(userTaskId);
                return "Deleted successfully";
            }
        }

        public List<Tasks> getUserTaskByUser(Long id){
            User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
            List<Tasks> list = new ArrayList<>();
            for(UserTasks userTask : userTasksRepository.findAllByUser(user))
                list.add(userTask.getTasks());

            return list;
        }
    }
