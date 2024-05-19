package com.example.futbol.dtos.requests;

import lombok.Data;

@Data
public class ContratoRequest {
    private String nombre;
    private int duracion;
    private double precioFichajeAnual;
}
