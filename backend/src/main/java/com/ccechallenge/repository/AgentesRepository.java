package com.ccechallenge.repository;

import com.ccechallenge.model.AgentesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentesRepository extends JpaRepository<AgentesModel, Long> {
}
