package com.example.demo.services;

import com.example.demo.domain.lane.dto.CreateLaneDTO;
import com.example.demo.domain.lane.dto.LaneDTO;
import com.example.demo.domain.lane.dto.UpdateLaneDTO;

import java.util.List;


public interface LaneService {

    LaneDTO createLane(CreateLaneDTO createLaneDTO);

    LaneDTO getLaneById(Long id);

    List<LaneDTO> getAllLanes(Long id);

    LaneDTO updateLane(Long id, UpdateLaneDTO updateLaneDTO);

    Void deleteLane(Long id);


}
