package com.example.futbol.dtos.responses;


import lombok.Data;

@Data
public class ContratoResponse {
    private Long id;
    private String nombre;
    private int duracion;
    private double precioFichajeAnual;
}
