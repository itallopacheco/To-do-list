package com.example.demo.repository;

import com.example.demo.domain.lane.Lane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaneRepository extends JpaRepository<Lane,Long> {



}
