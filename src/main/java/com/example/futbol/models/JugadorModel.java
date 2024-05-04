package com.example.futbol.models;

import org.springframework.stereotype.Component;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.lang.String;
import java.util.Set;

@Entity
@Table(name = "Jugadores")
@Getter
@Setter
@Component
public class JugadorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellido")
    private String apellido;

    @Column(name = "Camiseta")
    private Long num_camiseta;

    @Column(name = "Edad")
    private Integer edad;

    @Column(name = "Altura")
    private Integer alturaCm;

}
