package com.example.demo.services.imp;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.sprint.Sprint;
import com.example.demo.domain.sprint.dto.CreateSprintDTO;
import com.example.demo.domain.sprint.dto.SprintDTO;
import com.example.demo.domain.sprint.dto.UpdateSprintDTO;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.SprintRepository;
import com.example.demo.services.SprintService;
import com.example.demo.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SprintServiceImp implements SprintService {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    SprintRepository sprintRepository;

    @Override
    public SprintDTO create(CreateSprintDTO createSprintDTO) {
        Optional<Project> projectOptional = projectRepository.findById(createSprintDTO.getProjectId());
        if (projectOptional.isEmpty()){
            throw new EntityNotFoundException("Projeto nao encontrado para id: " +createSprintDTO.getProjectId());
        }
        Project project = projectOptional.get();
        Sprint sprint = new Sprint(
                createSprintDTO.getName(),
                createSprintDTO.getStarterDate(),
                createSprintDTO.getFinishDate(),
                project);
        sprintRepository.save(sprint);
        return new SprintDTO(sprint);
    }

    @Override
    public List<SprintDTO> getAll() {
        List<SprintDTO> sprintDTOS = new ArrayList<>();
        List<Sprint> sprints = sprintRepository.findAll();

        for (Sprint s : sprints) {
            SprintDTO sprintDTO = new SprintDTO(s);
            sprintDTOS.add(sprintDTO);
        }
        return sprintDTOS;
    }

    @Override
    public SprintDTO getById(Long id) {
        Optional<Sprint> sprintOptional = sprintRepository.findById(id);
        if (sprintOptional.isPresent()){
            return new SprintDTO(sprintOptional.get());
        } else {
            throw new EntityNotFoundException("Sprint nao encontrada para o id: " + id);
        }
    }

    @Override
    public SprintDTO update(Long id, UpdateSprintDTO sprintDTO) {
        Optional<Sprint> sprintOptional = sprintRepository.findById(id);
        if (sprintOptional.isEmpty()){
            throw  new EntityNotFoundException("Project not found for id: " + id);
        }
        Sprint oldSprint = sprintOptional.get();
        if (sprintDTO.getName() != null){
            oldSprint.setName(sprintDTO.getName());
        }
        if (sprintDTO.getStarterDate() != null){
            //TODO: validar se a data de inicio é anterior a data de fim seja do oldSprint ou do sprintDTO
            oldSprint.setStartDate(sprintDTO.getStarterDate());
        }
        if (sprintDTO.getFinishDate()!=null){
            oldSprint.setFinishDate(sprintDTO.getFinishDate());
        }

        sprintRepository.save(oldSprint);

        return new SprintDTO(oldSprint);
    }

    @Override
    public Void delete(Long id) {
        Optional<Sprint> sprintOptional = sprintRepository.findById(id);
        if (sprintOptional.isEmpty()){
            throw new EntityNotFoundException("Sprint nao encontrada para o id: " +id);
        }
        sprintRepository.delete(sprintOptional.get());
        return null;
    }
}
