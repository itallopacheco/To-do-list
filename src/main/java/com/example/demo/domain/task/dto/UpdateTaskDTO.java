package com.example.demo.domain.task.dto;

import com.example.demo.domain.sprint.dto.SprintDTO;
import com.example.demo.domain.user.dto.UserDTO;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateTaskDTO {

    private String name;
    private String description;
    private Long responsibleId;
    private Long sprintId;
    private Integer score;
    private Date startDate;
    private Date finishDate;

}
