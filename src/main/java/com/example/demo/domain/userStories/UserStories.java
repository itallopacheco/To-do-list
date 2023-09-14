package com.example.demo.domain.userStories;


import com.example.demo.domain.project.Project;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_stories")
@Data
@NoArgsConstructor
public class UserStories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String description;

    @ManyToOne
    private Project project;



    public UserStories(String name, String description, Project project){
        this.name = name;
        this.description = description;
        this.project = project;
    }



}
