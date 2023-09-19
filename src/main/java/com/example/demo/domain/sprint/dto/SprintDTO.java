package com.example.demo.domain.sprint.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SprintDTO {

    private Long id;

    private String name;

    private Date starterDate;

    private Date finishDate;

    private Long projectId;



}
