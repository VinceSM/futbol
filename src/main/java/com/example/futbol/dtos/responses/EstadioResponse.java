package com.example.futbol.dtos.responses;

import lombok.Data;

@Data
public class EstadioResponse {
    private Long id;
    private String nombre;
    private String ubicacion;
    private Long capacidad;
    private String tipoDeTerreno;

}
