package com.example.futbol.models;

import org.springframework.stereotype.Component;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

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

    @Column(name = "Liga")
    private String nombreLiga;

    @Column(name = "Apodo")
    private String apodo;

    @OneToOne
    @JoinTable(name = "equipo_estadio", // Nombre de la tabla de unión
            joinColumns = @JoinColumn(name = "equipo_id"), // Clave foránea que apunta a Equipo
            inverseJoinColumns = @JoinColumn(name = "estadio_id")) // Clave foránea que apunta a Jugador
    private EstadioModel estadio;

}
