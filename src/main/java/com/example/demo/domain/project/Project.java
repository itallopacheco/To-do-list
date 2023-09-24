package com.example.demo.domain.project;

import com.example.demo.domain.lane.Lane;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = true)
    private String description;

    @ManyToOne
    private User owner;

    @ManyToMany
    @JoinTable(
            name = "project_user",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> members = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Lane> lanes = new ArrayList<>();

    public Project(String name, String description, User owner){
        this.name = name;
        this.description = description;
        this.owner = owner;
    }
    public void addMember(User user){
        members.add(user);
    }


    public List<User> getAllMembers() {
        return new ArrayList<>(members);
    }

}
