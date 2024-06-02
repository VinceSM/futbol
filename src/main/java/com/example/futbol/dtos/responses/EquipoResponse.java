package com.example.futbol.dtos.responses;

import com.example.futbol.models.EstadioModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EquipoResponse {
    private Long id;
    private String nombreEquipo;
    private String nombreLiga;
    private String apodo;
    private EstadioModel estadio;
    private String Posicion;
}
