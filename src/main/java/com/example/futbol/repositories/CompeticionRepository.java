package com.example.futbol.repositories;

import com.example.futbol.models.CompeticionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompeticionRepository extends JpaRepository<CompeticionModel, Long> {
}

