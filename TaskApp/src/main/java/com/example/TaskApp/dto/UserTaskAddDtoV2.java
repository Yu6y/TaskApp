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
public class UserTaskAddDtoV2 {
    private Long taskId;
    private String title;
    private String description;
}
