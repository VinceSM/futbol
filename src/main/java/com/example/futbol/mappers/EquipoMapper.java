package com.example.futbol.mappers;

import com.example.futbol.dtos.requests.EquipoRequest;
import com.example.futbol.dtos.responses.EquipoResponse;
import com.example.futbol.models.EquipoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipoMapper {
    @Autowired
    private JugadorMapper jugadorMapper;

    public EquipoModel mapToEquipoModel(EquipoRequest equipoRequest) {
        EquipoModel equipo = new EquipoModel();
        equipo.setNombreLiga(equipoRequest.getNombreLiga());
        equipo.setApodo(equipoRequest.getApodo());
        equipo.setNombreEquipo(equipoRequest.getNombreEquipo());
        equipo.setEstadio(equipoRequest.getEstadio());
        return equipo;
    }

    public EquipoResponse mapToEquipoResponse(EquipoModel equipo) {
        EquipoResponse equipoResponse = new EquipoResponse();
        equipoResponse.setId(equipo.getId());
        equipoResponse.setNombreEquipo(equipo.getNombreEquipo());
        equipoResponse.setApodo(equipo.getApodo());
        equipoResponse.setNombreLiga(equipo.getNombreLiga());
        equipoResponse.setEstadio(equipo.getEstadio());
        return equipoResponse;
    }
}