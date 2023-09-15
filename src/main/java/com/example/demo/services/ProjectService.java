package com.example.demo.services;

import com.example.demo.domain.project.dto.CreateProjectDTO;
import com.example.demo.domain.project.dto.ProjectDTO;
import com.example.demo.domain.project.dto.UpdateProjectDTO;
import com.example.demo.domain.user.dto.UserDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO createProject(CreateProjectDTO projectDTO);

    ProjectDTO getProjectById(ProjectDTO projectDTO);

    List<ProjectDTO> getAllProjects();

    Void addMember(UserDTO userDTO);

    ProjectDTO updateProject(Long id, UpdateProjectDTO updateProjectDTO);

    Void deleteProject(Long id);

}
