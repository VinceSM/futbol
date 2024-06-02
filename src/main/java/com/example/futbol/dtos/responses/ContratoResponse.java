package com.example.futbol.dtos.responses;


import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class ContratoResponse {
    private Long id;
    private String descripcion;
    private int duracionAnual;
    private double precioFichaje;
    private Date fechaInicio;
    private Date fechaFin;
}
