package com.example.demo.domain.lane;

import com.example.demo.domain.lane.dto.LaneDTO;
import com.example.demo.domain.project.Project;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lanes")
@Data
@NoArgsConstructor
public class Lane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Project project;


    public Lane(String name, Project project){
        this.name = name;
        this.project = project;
    }


}
