package com.example.futbol.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@Table(name = "equipos")
@Component
public class EquipoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Nombre")
    private String nombreEquipo;

    @Column(name = "Posicion")
    private String posicion;

    @Column(name = "Apodo")
    private String apodo;

    @OneToOne
    @JoinTable(name = "equipo_estadio",
            joinColumns = @JoinColumn(name = "equipo_id"),
            inverseJoinColumns = @JoinColumn(name = "estadio_id"))
    private EstadioModel estadio;

    @OneToOne
    @JoinTable(name = "equipo_competicion",
            joinColumns = @JoinColumn(name = "equipo_id"),
            inverseJoinColumns = @JoinColumn(name = "competicion_id"))
    private CompeticionModel competicion;
}
