package com.example.futbol.dtos.requests;


import com.example.futbol.models.EquipoModel;
import lombok.Data;

@Data
public class EstadioRequest {
    private String nombre;
    private String ubicacion;
    private Long capacidad;
    private String tipoDeTerreno;

}
