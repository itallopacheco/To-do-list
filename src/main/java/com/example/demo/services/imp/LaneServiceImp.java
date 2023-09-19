package com.example.demo.services.imp;

import com.example.demo.domain.lane.Lane;
import com.example.demo.domain.lane.dto.CreateLaneDTO;
import com.example.demo.domain.lane.dto.LaneDTO;
import com.example.demo.domain.lane.dto.UpdateLaneDTO;
import com.example.demo.domain.project.Project;
import com.example.demo.repository.LaneRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.services.LaneService;
import com.example.demo.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LaneServiceImp implements LaneService {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    LaneRepository laneRepository;

    @Override
    public LaneDTO createLane(CreateLaneDTO createLaneDTO) {
        Optional<Project> project = projectRepository.findById(createLaneDTO.getProjectId());
        if (project.isPresent()) {
            Lane lane = new Lane(
                    createLaneDTO.getName(),
                    project.get()
            );
            laneRepository.save(lane);
            project.get().getLanes().add(lane);
            LaneDTO laneDTO = new LaneDTO(lane);
            return laneDTO;
        } else {
            throw new EntityNotFoundException("Projeto com o ID especificado não foi encontrado: " + createLaneDTO.getProjectId());
        }
    }

    @Override
    public LaneDTO getLaneById(Long id) {
        Optional<Lane> lane = laneRepository.findById(id);
        if (lane.isPresent()){
            LaneDTO laneDTO = new LaneDTO(lane.get());
            return laneDTO;
        } else {
            throw new EntityNotFoundException("Lane com o ID especificado não foi encontrado: " + id);
        }
    }

    @Override
    public List<LaneDTO> getAllLanes(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()){
            List<LaneDTO> lanes = new ArrayList<>();
            for (Lane l: project.get().getLanes()) {
                LaneDTO lane = new LaneDTO(l);
                lanes.add(lane);
            }
            return lanes;
        } else {
            throw new EntityNotFoundException("Projeto com o ID especificado não foi encontrado: " + id);
        }
    }

    @Override
    public LaneDTO updateLane(Long id, UpdateLaneDTO updateLaneDTO) {
        Optional<Lane> lane = laneRepository.findById(id);
        if (lane.isPresent()){
            Lane oldLane = lane.get();
            oldLane.setName(updateLaneDTO.getName());
            laneRepository.save(oldLane);
            LaneDTO laneDTO = new LaneDTO(oldLane);
            return laneDTO;
        } else {
            throw new EntityNotFoundException("Lane com o ID especificado não foi encontrado: " + id);
        }
    }

    @Override
    public Void deleteLane(Long id) {
        Optional<Lane> laneOptional = laneRepository.findById(id);
        if (laneOptional.isPresent()){
            Lane lane = laneOptional.get();
            laneRepository.delete(lane);
            return null;
        } else {
            throw new EntityNotFoundException("Lane com o ID especificado não foi encontrado: " + id);
        }
    }
}
