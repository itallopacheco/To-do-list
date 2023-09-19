package com.example.demo.domain.lane.dto;

import com.example.demo.domain.lane.Lane;
import com.example.demo.domain.project.dto.ProjectDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LaneDTO {

    private Long id;

    private String name;

    private Long projectId;

    public LaneDTO(Lane lane){
        this.id = lane.getId();
        this.name = lane.getName();
        this.projectId = lane.getProject().getId();
    }
}
