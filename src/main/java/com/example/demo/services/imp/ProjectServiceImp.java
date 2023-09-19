package com.example.demo.services.imp;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.user.User;
import com.example.demo.domain.project.dto.CreateProjectDTO;
import com.example.demo.domain.project.dto.ProjectDTO;
import com.example.demo.domain.project.dto.UpdateProjectDTO;
import com.example.demo.domain.user.dto.UserDTO;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.ProjectService;
import com.example.demo.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ProjectDTO createProject(CreateProjectDTO projectDTO) {
        Optional<User> user = userRepository.findById(projectDTO.getOwnerId());
        if (user.isPresent()){
            UserDTO owner = new UserDTO(user.get());

            Project project = new Project(
                    projectDTO.getName(),
                    projectDTO.getDescription(),
                    user.get()
            );
            projectRepository.save(project);
            ProjectDTO response = new ProjectDTO(project);
            return response;
        } else {
            throw new com.example.demo.services.exceptions.EntityNotFoundException("User not found for id: "+projectDTO.getOwnerId());
        }
    }

    @Override
    public ProjectDTO getProjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()){
            return new ProjectDTO(project.get());
        }else {
            throw new com.example.demo.services.exceptions.EntityNotFoundException("Project not found for id: "+id);
        }
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDTO> projectsDTO = new ArrayList<>();

        for (Project p :projects) {
            ProjectDTO projectDTO = new ProjectDTO(p);
            projectsDTO.add(projectDTO);
        }
        return projectsDTO;
    }

    @Override
    public Void addMember(Long projectId, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Project> projectOptional = projectRepository.findById(projectId);

        if (!userOptional.isPresent() || !projectOptional.isPresent()) {
            throw new EntityNotFoundException("Invalid user or project IDs: User ID=" + userId + ", Project ID=" + projectId);
        }
        Project project = projectOptional.get();
        User user = userOptional.get();

        project.addMember(user);
        projectRepository.save(project);

        return null;
    }

    @Override
    public Void removeMember(Long projectId, Long userId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (!userOptional.isPresent() || !projectOptional.isPresent()) {
            throw new EntityNotFoundException("Invalid user or project IDs: User ID=" + userId + ", Project ID=" + projectId);
        }

        Project project = projectOptional.get();
        User user = userOptional.get();

        project.getMembers().remove(user);
        projectRepository.save(project);

        return null;
    }


    @Override
    public ProjectDTO updateProject(Long id, UpdateProjectDTO updateProjectDTO) {
        Optional<Project> oldProject = projectRepository.findById(id);
        if (oldProject.isPresent()){
            Project project = oldProject.get();
            project.setName(updateProjectDTO.getName());
            project.setDescription(updateProjectDTO.getDescription());
            projectRepository.save(project);
            ProjectDTO projectDTO = new ProjectDTO(project);
            return projectDTO;
        } else {
            throw new EntityNotFoundException("Projeto com ID " + id + " não encontrado");
        }
    }

    @Override
    public Void deleteProject(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()){
            projectRepository.delete(project.get());
            return null;
        } else {
            throw new EntityNotFoundException("Projeto com ID " + id + " não encontrado");
        }
    }
}
