package com.example.demo.domain.task.dto;

import com.example.demo.domain.sprint.Sprint;
import com.example.demo.domain.sprint.dto.SprintDTO;
import com.example.demo.domain.task.Task;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;

    private String name;

    private String description;

    private Date creationDate;

    private Long responsibleId;

    private List<UserDTO> members = new ArrayList<>();

    private Long sprintId;

    private Long projectId;

    private Integer score;




}
