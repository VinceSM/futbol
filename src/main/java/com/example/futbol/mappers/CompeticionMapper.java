package com.example.futbol.mappers;

import com.example.futbol.dtos.requests.CompeticionRequest;
import com.example.futbol.dtos.responses.CompeticionResponse;
import com.example.futbol.models.CompeticionModel;
import org.springframework.stereotype.Component;

@Component
public class CompeticionMapper {

    public CompeticionModel mapToCompeticionModel(CompeticionRequest competicionRequest) {
        CompeticionModel competicion = new CompeticionModel();
        competicion.setTipo(competicionRequest.getTipo());
        competicion.setNombre(competicionRequest.getNombre());
        return competicion;
    }

    public CompeticionResponse mapToCompeticionResponse(CompeticionModel competicion) {
        CompeticionResponse competicionResponse = new CompeticionResponse();
        competicionResponse.setId(competicion.getId());
        competicionResponse.setTipo(competicion.getTipo());
        competicionResponse.setNombre(competicion.getNombre());
        return competicionResponse;
    }
}
