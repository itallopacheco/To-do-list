package com.example.demo.domain.task.dto;

import com.example.demo.domain.sprint.Sprint;
import com.example.demo.domain.sprint.dto.SprintDTO;
import com.example.demo.domain.task.Task;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.dto.UserDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data

public class TaskDTO {

    private Long id;

    private String name;

    private String description;

    private Date creationDate;

    private UserDTO responsible;

    private List<UserDTO> members = new ArrayList<>();

    private SprintDTO sprint;

    private Long projectId;

    private Integer score;

    public TaskDTO(Long id,String name, String description, Date creationDate, UserDTO responsible, List<UserDTO> members, SprintDTO sprint, Long projectId, Integer score) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.responsible = responsible;
        this.members = members;
        this.sprint = sprint;
        this.projectId = projectId;
        this.score = score;
    }


}
