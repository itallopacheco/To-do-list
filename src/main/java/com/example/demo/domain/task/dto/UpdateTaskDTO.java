package com.example.demo.domain.task.dto;

import com.example.demo.domain.sprint.dto.SprintDTO;
import com.example.demo.domain.user.dto.UserDTO;
import lombok.Data;

@Data
public class UpdateTaskDTO {

    String name;
    private String description;
    private UserDTO responsible;
    private SprintDTO sprint;
    private Integer score;

}
