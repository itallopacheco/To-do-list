package com.example.demo.controller;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.dto.CreateProjectDTO;
import com.example.demo.domain.project.dto.ProjectDTO;
import com.example.demo.domain.user.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping("/add")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody CreateProjectDTO createProjectDTO, @AuthenticationPrincipal User authenticatedUser){
        createProjectDTO.setOwnerId(authenticatedUser.getId());
        return new ResponseEntity<ProjectDTO>(projectService.createProject(createProjectDTO), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectDTO>> getAll(){
        return new ResponseEntity<List<ProjectDTO>>(projectService.getAllProjects(),HttpStatus.OK);
    }

    @PostMapping("/{id}/add-member/{idUser}")
    public ResponseEntity<?> addMember(@PathVariable(name = "id")long projetoId, @PathVariable(name = "idUser") Long userId){
        projectService.addMember(projetoId,userId);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
