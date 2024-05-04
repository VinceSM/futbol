package com.example.futbol.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "Estadios")
@Data
public class EstadioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Ubicacion")
    private String ubicacion;

    @Column(name = "Capacidad")
    private Long capacidad;

    @Column(name = "Terreno")
    private String tipoDeTerreno;
}
