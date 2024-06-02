package com.example.futbol.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Entity
@Component
@Table(name = "contratos")
public class ContratoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "DuracionAnual")
    private int duracionAnual;

    @Column(name = "PrecioDelFichaje")
    private double precioFichaje;

    @Column(name = "FechaInicio")
    private Date fechaInicio;

    @Column(name = "FechaFin")
    private Date fechaFin;
}
