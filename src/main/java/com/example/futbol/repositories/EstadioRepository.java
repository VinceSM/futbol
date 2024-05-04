package com.example.futbol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.futbol.models.EstadioModel;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadioRepository extends JpaRepository<EstadioModel, Long> {
}
