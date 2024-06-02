package com.example.futbol.dtos.requests;

import com.example.futbol.models.EstadioModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EquipoRequest {
    private String nombreEquipo;
    private String nombreLiga;
    private String apodo;
    private EstadioModel estadio;
    private String Posicion;

}
