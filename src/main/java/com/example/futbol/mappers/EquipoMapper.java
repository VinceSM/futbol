package com.example.futbol.mappers;

import com.example.futbol.dtos.requests.EquipoRequest;
import com.example.futbol.dtos.responses.EquipoResponse;
import com.example.futbol.models.EquipoModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class EquipoMapper {

    public EquipoModel mapToEquipoModel(EquipoRequest equipoRequest) {
        EquipoModel equipo = new EquipoModel();
        equipo.setNombreEquipo(equipoRequest.getNombreEquipo());
        equipo.setApodo(equipoRequest.getApodo());
        equipo.setEstadio(equipoRequest.getEstadio());
        equipo.setPosicion(equipoRequest.getPosicion());
        equipo.setCompeticion(equipoRequest.getCompeticion());
        return equipo;
    }

    public EquipoResponse mapToEquipoResponse(EquipoModel equipo) {
        EquipoResponse equipoResponse = new EquipoResponse();
        equipoResponse.setId(equipo.getId());
        equipoResponse.setNombreEquipo(equipo.getNombreEquipo());
        equipoResponse.setApodo(equipo.getApodo());
        equipoResponse.setEstadio(equipo.getEstadio());
        equipoResponse.setPosicion(equipo.getPosicion());
        equipoResponse.setCompeticion(equipo.getCompeticion());
        return equipoResponse;
    }
}
