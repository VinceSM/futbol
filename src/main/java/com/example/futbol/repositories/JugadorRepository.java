package com.example.futbol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.futbol.models.JugadorModel;

@Repository
public interface JugadorRepository extends JpaRepository<JugadorModel, Long> {
}
