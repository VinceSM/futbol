package com.example.futbol.dtos.requests;

import lombok.Data;

import java.util.Date;

@Data
public class ContratoRequest {
    private String descripcion;
    private int duracionAnual;
    private double precioFichaje;
    private Date fechaInicio;
    private Date fechaFin;
}
