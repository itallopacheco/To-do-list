package com.example.demo.controller;

import com.example.demo.domain.project.CreateProjectDTO;
import com.example.demo.domain.project.Project;
import com.example.demo.domain.user.User;
import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @PostMapping("/new")
    public ResponseEntity createProject(@RequestBody CreateProjectDTO createProjectDTO, @AuthenticationPrincipal User authenticatedUser){

        Project newProject = new Project(
                createProjectDTO.getName()
        );
        newProject.setOwner(authenticatedUser);

        Project savedProject = projectRepository.save(newProject);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProject.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
