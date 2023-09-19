package com.example.demo.services;

import com.example.demo.domain.task.dto.CreateTaskDTO;
import com.example.demo.domain.task.dto.TaskDTO;
import com.example.demo.domain.task.dto.UpdateTaskDTO;

import java.util.List;

public interface TaskService {

    TaskDTO create(Long projectId, Long userId, CreateTaskDTO createTaskDTO);

    List<TaskDTO> getAll();

    TaskDTO getById(Long taskId);

    TaskDTO update(Long id, UpdateTaskDTO updateTaskDTO);

    Void delete(Long id);

    TaskDTO addMember(Long idTask, Long idMember);

    TaskDTO removeMember(Long idTask, Long idMember);




}
