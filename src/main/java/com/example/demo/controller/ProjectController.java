package com.example.demo.controller;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.dto.CreateProjectDTO;
import com.example.demo.domain.project.dto.ProjectDTO;
import com.example.demo.domain.project.dto.UpdateProjectDTO;
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

    @PostMapping()
    public ResponseEntity<ProjectDTO> createProject(@RequestBody CreateProjectDTO createProjectDTO, @AuthenticationPrincipal User authenticatedUser){
        createProjectDTO.setOwnerId(authenticatedUser.getId());
        return new ResponseEntity<ProjectDTO>(projectService.createProject(createProjectDTO), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ProjectDTO>> getAll(){
        return new ResponseEntity<List<ProjectDTO>>(projectService.getAllProjects(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public  ResponseEntity<ProjectDTO> getById(@PathVariable(name = "id") long id){
        return new ResponseEntity<ProjectDTO>(projectService.getProjectById(id),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProjectDTO> update(@PathVariable(name = "id") long id, @RequestBody UpdateProjectDTO updateProjectDTO){
        return new ResponseEntity<ProjectDTO>(projectService.updateProject(id,updateProjectDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id")long id){

        return new ResponseEntity<>((projectService.deleteProject(id)),HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/add-member/{idUser}")
    public ResponseEntity<?> addMember(@PathVariable(name = "id")long projetoId, @PathVariable(name = "idUser") Long userId){
        projectService.addMember(projetoId,userId);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/{id}/remove-member/{idUser}")
    public ResponseEntity<?> deleteMember(@PathVariable(name = "id")long projetoId, @PathVariable(name = "idUser") Long userId){
        projectService.removeMember(projetoId,userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
