package com.example.futbol.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "competiciones")
@Data
@Component
public class CompeticionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Tipo")
    private String tipo;

    @Column(name = "Nombre")
    private String nombre;
}
