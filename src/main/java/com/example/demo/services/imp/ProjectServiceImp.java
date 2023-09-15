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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

            ProjectDTO response = new ProjectDTO(project);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public ProjectDTO getProjectById(ProjectDTO projectDTO) {
        return null;
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return null;
    }

    @Override
    public Void addMember(UserDTO userDTO) {
        return null;
    }

    @Override
    public ProjectDTO updateProject(Long id, UpdateProjectDTO updateProjectDTO) {
        return null;
    }

    @Override
    public Void deleteProject(Long id) {
        return null;
    }
}
