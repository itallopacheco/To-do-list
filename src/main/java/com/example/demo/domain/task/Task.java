package com.example.demo.domain.task;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.sprint.Sprint;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String description;

    private Date creationDate;

    @OneToOne
    private User responsible;

    @ManyToMany
    @JoinTable(
            name = "task_member",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> members = new ArrayList<>();

    @ManyToOne
    private Sprint sprint;

    @ManyToOne
    private Project project;

    private Integer score;


    public Task(String name, String description, Project project, Integer score){
        this.name = name;
        this.description = description;
        this.project = project;
        this.score = score;
    }

}
