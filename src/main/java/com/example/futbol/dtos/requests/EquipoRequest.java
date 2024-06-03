package com.example.futbol.dtos.requests;

import com.example.futbol.models.EstadioModel;
import com.example.futbol.models.CompeticionModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipoRequest {
    private String nombreEquipo;
    private String apodo;
    private EstadioModel estadio;
    private String posicion;
    private CompeticionModel competicion;
}
