package com.example.demo.controller;


import com.example.demo.domain.sprint.dto.CreateSprintDTO;
import com.example.demo.domain.sprint.dto.SprintDTO;
import com.example.demo.domain.sprint.dto.UpdateSprintDTO;
import com.example.demo.services.SprintService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sprints")
public class SprintController {

    @Autowired
    SprintService sprintService;

    @PostMapping
    public ResponseEntity<SprintDTO> create(@RequestBody @Valid CreateSprintDTO sprintDTO){
        return new ResponseEntity<SprintDTO>(sprintService.create(sprintDTO), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<SprintDTO>> getAll(){
        return new ResponseEntity<List<SprintDTO>>(sprintService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<SprintDTO> getById(@PathVariable(name = "id")long id){
        return new ResponseEntity<SprintDTO>(sprintService.getById(id),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<SprintDTO> update(@PathVariable(name = "id")long id, @RequestBody @Valid UpdateSprintDTO sprintDTO){
        return new ResponseEntity<SprintDTO>(sprintService.update(id,sprintDTO),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id")long id){
        return new ResponseEntity<>(sprintService.delete(id),HttpStatus.OK);
    }

}
