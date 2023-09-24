package com.example.demo.repository;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByUsername(String login);

    Optional<User> findUserByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findByNameContaining(String name);

    @Query("FROM User u WHERE u.name like %:searchTerm% OR u.username like %:searchTerm%")
    Page<User> search(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("FROM Project p WHERE p.owner.id = :id")
    Page<Project> getProjects(Long id, Pageable pageable);

}
