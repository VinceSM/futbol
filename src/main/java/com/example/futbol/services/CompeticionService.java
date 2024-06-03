package com.example.futbol.services;

import com.example.futbol.dtos.requests.CompeticionRequest;
import com.example.futbol.dtos.responses.CompeticionResponse;
import com.example.futbol.mappers.CompeticionMapper;
import com.example.futbol.models.CompeticionModel;
import com.example.futbol.repositories.CompeticionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompeticionService {

    @Autowired
    private CompeticionRepository competicionRepository;

    @Autowired
    private CompeticionMapper competicionMapper;

    @Transactional
    public CompeticionResponse crearCompeticion(CompeticionRequest competicionRequest) {
        try {
            CompeticionModel competicion = competicionMapper.mapToCompeticionModel(competicionRequest);
            CompeticionModel nuevaCompeticion = competicionRepository.save(competicion);
            return competicionMapper.mapToCompeticionResponse(nuevaCompeticion);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear competición", e);
        }
    }

    @Transactional
    public List<CompeticionResponse> listarCompeticiones() {
        try {
            List<CompeticionModel> competiciones = competicionRepository.findAll();
            return competiciones.stream()
                    .map(competicionMapper::mapToCompeticionResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al listar competiciones", e);
        }
    }

    @Transactional
    public CompeticionResponse modificarCompeticion(Long id, CompeticionRequest competicionRequest) {
        try {
            CompeticionModel competicion = competicionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Competición no encontrada con id: " + id));

            competicion.setTipo(competicionRequest.getTipo());
            competicion.setNombre(competicionRequest.getNombre());

            CompeticionModel competicionModificada = competicionRepository.save(competicion);
            return competicionMapper.mapToCompeticionResponse(competicionModificada);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al modificar competición", e);
        }
    }

    @Transactional
    public void eliminarCompeticion(Long id) {
        try {
            CompeticionModel competicion = competicionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Competición no encontrada con id: " + id));
            competicionRepository.delete(competicion);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar competición", e);
        }
    }

    @Transactional
    public CompeticionResponse obtenerCompeticionPorId(Long id) {
        try {
            CompeticionModel competicion = competicionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Competición no encontrada con id: " + id));
            return competicionMapper.mapToCompeticionResponse(competicion);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener competición", e);
        }
    }
}

