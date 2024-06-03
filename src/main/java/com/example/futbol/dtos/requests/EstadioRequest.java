package com.example.futbol.dtos.requests;

import lombok.Data;

@Data
public class EstadioRequest {
    private String nombre;
    private String ubicacion;
    private Long capacidad;
    private String tipoDeTerreno;

}
