package com.example.futbol.mappers;

import com.example.futbol.dtos.requests.ContratoRequest;
import com.example.futbol.dtos.responses.ContratoResponse;
import com.example.futbol.models.ContratoModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ContratoMapper {
    public ContratoModel mapToContratoModel(ContratoRequest contratoRequest) {
        ContratoModel contrato = new ContratoModel();
        contrato.setDescripcion(contratoRequest.getDescripcion());
        contrato.setDuracionAnual(contratoRequest.getDuracionAnual());
        contrato.setFechaInicio(contratoRequest.getFechaInicio());
        contrato.setFechaFin(contratoRequest.getFechaFin());
        contrato.setPrecioFichaje(contratoRequest.getPrecioFichaje());
        return contrato;
    }

    public ContratoResponse mapToContratoResponse(ContratoModel contrato) {
        ContratoResponse contratoResponse = new ContratoResponse();
        contratoResponse.setId(contrato.getId());
        contratoResponse.setDescripcion(contrato.getDescripcion());
        contratoResponse.setDuracionAnual(contrato.getDuracionAnual());
        contratoResponse.setFechaInicio(contrato.getFechaInicio());
        contratoResponse.setFechaFin(contrato.getFechaFin());
        contratoResponse.setPrecioFichaje(contrato.getPrecioFichaje());
        return contratoResponse;
    }
}
