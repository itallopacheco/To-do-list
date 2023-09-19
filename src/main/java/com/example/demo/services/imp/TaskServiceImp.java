package com.example.demo.services.imp;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.sprint.dto.SprintDTO;
import com.example.demo.domain.task.Task;
import com.example.demo.domain.task.dto.CreateTaskDTO;
import com.example.demo.domain.task.dto.TaskDTO;
import com.example.demo.domain.task.dto.UpdateTaskDTO;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.dto.UserDTO;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public TaskDTO create(Long projectId, Long userId, CreateTaskDTO createTaskDTO) {
        Optional<Project> project = projectRepository.findById(projectId);
        Optional<User> user = userRepository.findById(userId);

        if (! project.isPresent()){
            throw new EntityNotFoundException("Nao foi possivel encontrar projeto de id: " + project);
        }
        if (! user.isPresent()){
            throw new EntityNotFoundException("Nao foi possivel encontrar usuario de id: " + userId);
        }


        Task task = new Task(
                createTaskDTO.getName(),
                createTaskDTO.getDescription(),
                project.get()
                );
        task.setCreationDate(new Date());
        taskRepository.save(task);


        List<UserDTO> membersDTO = new ArrayList<>();
        UserDTO userDTO = new UserDTO(user.get());
        for (User u : task.getMembers()) {
            UserDTO uDTO = new UserDTO(u);
            membersDTO.add(uDTO);
        }
        SprintDTO sprintDTO = new SprintDTO();
        TaskDTO taskDTO = new TaskDTO(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getCreationDate(),
                userDTO,
                membersDTO,
                sprintDTO,
                projectId,
                task.getScore()
        );

        return taskDTO;

    }

    @Override
    public TaskDTO getAll(Long projectId) {
        return null;
    }

    @Override
    public TaskDTO getById(Long taskId) {
        return null;
    }

    @Override
    public TaskDTO update(Long id, UpdateTaskDTO updateTaskDTO) {
        return null;
    }

    @Override
    public Void delete(Long id) {
        return null;
    }

    @Override
    public TaskDTO addMember(Long idTask, Long idMember) {
        return null;
    }

    @Override
    public TaskDTO removeMember(Long idTask, Long idMember) {
        return null;
    }
}
