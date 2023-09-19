package com.example.demo.domain.sprint.dto;

import com.example.demo.domain.sprint.Sprint;
import lombok.Data;

import java.util.Date;

@Data
public class SprintDTO {

    private Long id;

    private String name;

    private Date starterDate;

    private Date finishDate;

    private Long projectId;

    public SprintDTO(Sprint sprint){
        this.id = sprint.getId();
        this.name = sprint.getName();
        this.starterDate = sprint.getStartDate();
        this.finishDate = sprint.getFinishDate();
        this.projectId = sprint.getProject().getId();
    }

}
