package com.example.TaskApp.dto;

import com.example.TaskApp.model.Tasks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTaskAddDto {
    private Long userId;
    private Tasks task;
}
