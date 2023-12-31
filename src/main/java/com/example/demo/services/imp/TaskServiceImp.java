package com.example.demo.services.imp;

import com.example.demo.domain.project.Project;
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
import com.example.demo.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

        if (project.isEmpty() && user.isEmpty()){
            throw new EntityNotFoundException("Nao foi possivel encontrar projeto de id: " + project + "\n"
            +"Nao foi possivel encontrar usuario de id: " + userId);
        }
        if (project.isEmpty()){
            throw new EntityNotFoundException("Nao foi possivel encontrar projeto de id: " + project);
        }
        if (user.isEmpty()){
            throw new EntityNotFoundException("Nao foi possivel encontrar usuario de id: " + userId);
        }

        Project project1 = project.get();
        User user1 = user.get();

        Task task = new Task(
                createTaskDTO.getName(),
                createTaskDTO.getDescription(),
                project1,
                createTaskDTO.getScore()
        );
        taskRepository.save(task);

        return toDTO(task);
    }

    @Override
    public List<TaskDTO> getAll() {
        List<TaskDTO> taskDTOS = taskRepository.findAll().stream()
                .map(task -> toDTO(task))
                .toList();
        return taskDTOS;
    }

    @Override
    public TaskDTO getById(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isEmpty()) throw new EntityNotFoundException("Task not found for id: " +taskId);
        return toDTO(taskOptional.get());
    }

    @Override
    public TaskDTO update(Long id, UpdateTaskDTO updateTaskDTO) {



    }

    @Override
    public Void delete(Long id) {
        Optional<Task> taskOptional =  taskRepository.findById(id);
        if (taskOptional.isEmpty())throw new com.example.demo.services.exceptions.EntityNotFoundException("Task not found for id: " + id);
        taskRepository.delete(taskOptional.get());
        return null;
    }

    @Override
    public TaskDTO addMember(Long idTask, Long idMember) {
        Optional<Task> taskOptional = taskRepository.findById(idTask);
        Optional<User> userOptional = userRepository.findById(idMember);

        if (taskOptional.isEmpty() && userOptional.isEmpty()) throw new EntityNotFoundException("Task and user not found for id's: " + idTask + idMember);
        Task task = taskOptional.get();
        User user = userOptional.get();

        List<User> member = task.getMembers();
        // TODO: verificar se o usuário já existe na lista de membros
        member.add(user);
        taskRepository.save(task);
        return toDTO(task);
    }

    @Override
    public TaskDTO removeMember(Long idTask, Long idMember) {
        Optional<Task> taskOptional = taskRepository.findById(idTask);
        Optional<User> userOptional = userRepository.findById(idMember);

        if (taskOptional.isEmpty() && userOptional.isEmpty()) throw new EntityNotFoundException("Task and user not found for id's: " + idTask + idMember);
        Task task = taskOptional.get();
        User user = userOptional.get();

        List<User> member = task.getMembers();
        // TODO: verificar se o usuário nao esta na lista ?
        member.remove(user);
        taskRepository.save(task);
        return toDTO(task);
    }


    private TaskDTO toDTO(Task task){
        List<UserDTO> memberDTOs = task.getMembers().stream()
                .map(member -> new UserDTO(member))
                .toList();

        return new TaskDTO(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getCreationDate(),
                task.getResponsible().getId(),
                memberDTOs,
                task.getSprint().getId(),
                task.getProject().getId(),
                task.getScore()
        );
    }
}
