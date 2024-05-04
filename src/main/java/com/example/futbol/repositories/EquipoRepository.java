package com.example.futbol.repositories;

import com.example.futbol.models.EquipoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<EquipoModel, Long> {
}
