package com.example.demo.domain.project.dto;

import com.example.demo.domain.user.dto.UserDTO;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectDTO {

    private String name;

    @Lob
    private String description;

    @NotEmpty
    private Long ownerId;


}
