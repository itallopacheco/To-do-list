package com.example.demo.domain.sprint;

import com.example.demo.domain.project.Project;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "sprints")
@Data
@NoArgsConstructor
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date startDate;

    private Date finishDate;

    @ManyToOne
    private Project project;

    public Sprint(String name,Date startDate, Date finishDate, Project project){
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.project = project;
    }

}
