package com.example.demo.domain.project.dto;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProjectDTO {

    private Long id;

    private String name;

    private String description;

    private UserDTO owner;

    private List<UserDTO> members = new ArrayList<>();


    public ProjectDTO(Long id, String name, String description, UserDTO owner){
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
    }
    public List<UserDTO> getMembers(){
        return new ArrayList<>(members);
    }
    public void addMember(UserDTO userDTO){
        members.add(userDTO);
    }

    public ProjectDTO(Project project){
        List<UserDTO> membersDTO = new ArrayList<>();
        for (User u:project.getAllMembers()) {
            UserDTO user = new UserDTO(u);
            membersDTO.add(user);
        }

        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.owner = new UserDTO(project.getOwner());
        this.members = membersDTO;
    }
}
