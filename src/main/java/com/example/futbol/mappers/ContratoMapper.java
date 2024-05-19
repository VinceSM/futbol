package com.example.futbol.mappers;

import com.example.futbol.dtos.requests.ContratoRequest;
import com.example.futbol.dtos.responses.ContratoResponse;
import com.example.futbol.models.ContratoModel;
import org.springframework.stereotype.Component;

@Component
public class ContratoMapper {
    public ContratoModel mapToContratoModel(ContratoRequest contratoRequest) {
        ContratoModel contrato = new ContratoModel();
        contrato.setNombre(contratoRequest.getNombre());
        contrato.setDuracion(contratoRequest.getDuracion());
        contrato.setPrecioFichajeAnual(contratoRequest.getPrecioFichajeAnual());
        return contrato;
    }

    public ContratoResponse mapToContratoResponse(ContratoModel contrato) {
        ContratoResponse contratoResponse = new ContratoResponse();
        contratoResponse.setId(contrato.getId());
        contratoResponse.setNombre(contrato.getNombre());
        contratoResponse.setDuracion(contrato.getDuracion());
        contratoResponse.setPrecioFichajeAnual(contrato.getPrecioFichajeAnual());
        return contratoResponse;
    }
}
