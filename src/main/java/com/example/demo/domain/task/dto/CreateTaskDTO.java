package com.example.demo.domain.task.dto;

import com.example.demo.domain.user.dto.UserDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateTaskDTO {

    private String name;

    private String description;

    private Long projectId;

    private Integer score;

}
