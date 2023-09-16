package com.example.demo.services;

import com.example.demo.domain.project.dto.CreateProjectDTO;
import com.example.demo.domain.project.dto.ProjectDTO;
import com.example.demo.domain.project.dto.UpdateProjectDTO;
import com.example.demo.domain.user.dto.UserDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO createProject(CreateProjectDTO projectDTO);

    ProjectDTO getProjectById(Long id);

    List<ProjectDTO> getAllProjects();

    Void addMember(Long projectId, Long userId);

    Void removeMember(Long projectId, Long userId);

    ProjectDTO updateProject(Long id, UpdateProjectDTO updateProjectDTO);

    Void deleteProject(Long id);

}
