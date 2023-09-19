package com.example.demo.controller;

import com.example.demo.domain.lane.dto.CreateLaneDTO;
import com.example.demo.domain.lane.dto.LaneDTO;

import com.example.demo.domain.lane.dto.UpdateLaneDTO;
import com.example.demo.services.LaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lanes")
public class LaneController {

    @Autowired
    LaneService laneService;

    @PostMapping()
    public ResponseEntity<LaneDTO> createLane(@RequestBody CreateLaneDTO createLaneDTO){
        return new ResponseEntity<LaneDTO>(laneService.createLane(createLaneDTO), HttpStatus.CREATED);
    }

    @GetMapping("project/{id}")
    public ResponseEntity<List<LaneDTO>> getAllLanes(@PathVariable(name = "id")long id){
        return new ResponseEntity<List<LaneDTO>>(laneService.getAllLanes(id),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<LaneDTO> getLaneById(@PathVariable(name = "id")long id){
        return new ResponseEntity<LaneDTO>(laneService.getLaneById(id),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<LaneDTO> update(@PathVariable(name = "id") long id, @RequestBody UpdateLaneDTO updateLaneDTO){
        return new ResponseEntity<LaneDTO>(laneService.updateLane(id,updateLaneDTO),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id){
        laneService.deleteLane(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
