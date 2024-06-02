package com.example.futbol.mappers;

import com.example.futbol.dtos.requests.EstadioRequest;
import com.example.futbol.dtos.responses.EstadioResponse;
import com.example.futbol.models.EstadioModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class EstadioMapper {

    public EstadioModel mapToEstadioModel(EstadioRequest estadioRequest) {
        EstadioModel estadio = new EstadioModel();
        estadio.setNombre(estadioRequest.getNombre());
        estadio.setUbicacion(estadioRequest.getUbicacion());
        estadio.setCapacidad(estadioRequest.getCapacidad());
        estadio.setTipoDeTerreno(estadioRequest.getTipoDeTerreno());
        return estadio;
    }

    public EstadioResponse mapToEstadioResponse(EstadioModel estadio) {
        EstadioResponse estadioResponse = new EstadioResponse();
        estadioResponse.setId(estadio.getId());
        estadioResponse.setNombre(estadio.getNombre());
        estadioResponse.setUbicacion(estadio.getUbicacion());
        estadioResponse.setCapacidad(estadio.getCapacidad());
        estadioResponse.setTipoDeTerreno(estadio.getTipoDeTerreno());
        return estadioResponse;
    }
}
