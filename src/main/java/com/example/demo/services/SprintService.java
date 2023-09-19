package com.example.demo.services;

import com.example.demo.domain.sprint.dto.CreateSprintDTO;
import com.example.demo.domain.sprint.dto.SprintDTO;
import com.example.demo.domain.sprint.dto.UpdateSprintDTO;

import java.util.List;

public interface SprintService {

    SprintDTO create(CreateSprintDTO createSprintDTO);

    List<SprintDTO> getAll();

    SprintDTO getById(Long id);

    SprintDTO update(Long id, UpdateSprintDTO sprintDTO);

    Void delete(Long id);


}
