package com.example.futbol.dtos.responses;

import com.example.futbol.models.EquipoModel;
import lombok.Data;

@Data
public class EstadioResponse {
    private Long id;
    private String nombre;
    private String ubicacion;
    private Long capacidad;
    private String tipoDeTerreno;

}
