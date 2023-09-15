package com.example.demo.controller;

import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

//    @PostMapping("/new")
//    public ResponseEntity createProject(@RequestBody CreateProjectDTO createProjectDTO, @AuthenticationPrincipal User authenticatedUser){
//
//        Project newProject = new Project(
//                createProjectDTO.getName()
//        );
//        newProject.setOwner(authenticatedUser);
//
//        Project savedProject = projectRepository.save(newProject);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(savedProject.getId())
//                .toUri();
//
//        return ResponseEntity.created(location).build();
//    }

}
