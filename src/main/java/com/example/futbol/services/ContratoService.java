package com.example.futbol.services;

import com.example.futbol.dtos.requests.ContratoRequest;
import com.example.futbol.dtos.responses.ContratoResponse;
import com.example.futbol.mappers.ContratoMapper;
import com.example.futbol.models.ContratoModel;
import com.example.futbol.repositories.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContratoService {
    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private ContratoMapper contratoMapper;

    @Transactional
    public ContratoResponse crearContrato(ContratoRequest contratoRequest) {
        ContratoModel contrato = contratoMapper.mapToContratoModel(contratoRequest);
        ContratoModel nuevoContrato = contratoRepository.save(contrato);
        return contratoMapper.mapToContratoResponse(nuevoContrato);
    }

    @Transactional
    public List<ContratoResponse> listarContratos() {
        List<ContratoModel> contratos = contratoRepository.findAll();
        return contratos.stream()
                .map(contratoMapper::mapToContratoResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ContratoResponse modificarContrato(Long id, ContratoRequest contratoRequest) {
        ContratoModel contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato no encontrado con id: " + id));

        contrato.setDescripcion(contratoRequest.getDescripcion());
        contrato.setDuracionAnual(contratoRequest.getDuracionAnual());
        contrato.setFechaInicio(contratoRequest.getFechaInicio());
        contrato.setFechaFin(contratoRequest.getFechaFin());
        contrato.setPrecioFichaje(contratoRequest.getPrecioFichaje());

        ContratoModel contratoModificado = contratoRepository.save(contrato);
        return contratoMapper.mapToContratoResponse(contratoModificado);
    }

    @Transactional
    public void eliminarContrato(Long id) {
        ContratoModel contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrato no encontrado con id: " + id));
        contratoRepository.delete(contrato);
    }

    @Transactional
    public ContratoResponse obtenerContratoPorId(Long id) {
        ContratoModel contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato no encontrado con id: " + id));
        return contratoMapper.mapToContratoResponse(contrato);
    }
}
