package com.example.demo.domain.lane;

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

    private Integer order;

    @ManyToOne
    private Project project;


    public Lane(String name, Integer order, Project project){
        this.name = name;
        this.order = order;
        this.project = project;
    }

}
