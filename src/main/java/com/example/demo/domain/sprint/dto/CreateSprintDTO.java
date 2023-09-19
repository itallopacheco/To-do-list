package com.example.demo.domain.sprint.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CreateSprintDTO {


    private String name;
    private Date starterDate;

    private Date finishDate;

    private Long projectId;


}
