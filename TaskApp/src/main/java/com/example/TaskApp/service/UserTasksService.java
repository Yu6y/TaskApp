    package com.example.TaskApp.service;

    import com.example.TaskApp.dto.UserTaskAddDto;
    import com.example.TaskApp.dto.UserTaskDeleteDto;
    import com.example.TaskApp.dto.UserTaskGetByIdDto;
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


        public String addUserTask(UserTaskAddDto userTask) {
            User user = userRepository.findById(userTask.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found!"));

            Tasks task = userTask.getTask();

            // Sprawdź, czy zadanie istnieje, jeśli nie, zapisz je najpierw
            if (!tasksRepository.existsById(task.getTaskId())) {
                taskService.addTask(task);
            }

            // Pobierz zadanie z repozytorium, aby upewnić się, że jest zapisane
            task = taskService.getTaskById(task.getTaskId());

            // Sprawdź, czy relacja między użytkownikiem a zadaniem już istnieje
            if (userTasksRepository.findByUserAndTasks(user, task).isPresent()) {
                throw new RuntimeException("User task already exists!");
            }

            UserTasks userTaskAdd = new UserTasks();
            userTaskAdd.setUser(user);
            userTaskAdd.setId((long)1);
            userTaskAdd.setTasks(task);
            userTaskAdd.setAssignedAt(LocalDateTime.now());

            userTasksRepository.save(userTaskAdd);

            return "UserTask added successfully";
        }

        public String deleteUserTask(UserTaskDeleteDto userTask) {
            User user = userRepository.findById(userTask.getUserId()).orElseThrow(() -> new RuntimeException("User not found!"));
            Tasks task = tasksRepository.findById(userTask.getTaskId()).orElseThrow(() -> new RuntimeException("Task not found!"));
            UserTasks userTaskToDelete = userTasksRepository.findByUserAndTasks(user, task).orElseThrow(() -> new RuntimeException("User task not found!"));

            userTasksRepository.deleteById(userTaskToDelete.getId());
                return "Deleted successfully";
        }

        public List<Tasks> getUserTaskByUser(UserTaskGetByIdDto userTaskDto){
            User user = userRepository.findById(userTaskDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found!"));
            List<Tasks> list = new ArrayList<>();
            for(UserTasks userTask : userTasksRepository.findAllByUser(user))
                list.add(userTask.getTasks());

            return list;
        }
    }
