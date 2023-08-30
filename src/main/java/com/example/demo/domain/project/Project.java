package com.example.demo.domain.project;

import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.datasource.init.UncategorizedScriptException;

import java.net.UnknownServiceException;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private User owner;


    public Project(String name){
        this.name = name;
    }

}
