package com.example.futbol.services;

import com.example.futbol.dtos.requests.EstadioRequest;
import com.example.futbol.dtos.responses.EstadioResponse;
import com.example.futbol.mappers.EstadioMapper;
import com.example.futbol.models.EstadioModel;
import com.example.futbol.repositories.EstadioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadioService {

    @Autowired
    private EstadioRepository estadioRepository;

    @Autowired
    private EstadioMapper estadioMapper;

    @Transactional
    public EstadioResponse crearEstadio(EstadioRequest estadioRequest) {
        try {
            EstadioModel estadio = estadioMapper.mapToEstadioModel(estadioRequest);
            EstadioModel nuevoEstadio = estadioRepository.save(estadio);
            return estadioMapper.mapToEstadioResponse(nuevoEstadio);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear estadio", e);
        }
    }

    @Transactional
    public List<EstadioResponse> listarEstadios() {
        try {
            List<EstadioModel> estadios = estadioRepository.findAll();
            return estadios.stream()
                    .map(estadioMapper::mapToEstadioResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al listar estadios", e);
        }
    }

    @Transactional
    public EstadioResponse modificarEstadio(Long id, EstadioRequest estadioRequest) {
        try {
            EstadioModel estadio = estadioRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Estadio no encontrado con id: " + id));

            estadio.setNombre(estadioRequest.getNombre());
            estadio.setUbicacion(estadioRequest.getUbicacion());
            estadio.setCapacidad(estadioRequest.getCapacidad());
            estadio.setTipoDeTerreno(estadioRequest.getTipoDeTerreno());

            EstadioModel estadioModificado = estadioRepository.save(estadio);
            return estadioMapper.mapToEstadioResponse(estadioModificado);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al modificar estadio", e);
        }
    }

    @Transactional
    public void eliminarEstadio(Long id) {
        try {
            EstadioModel estadio = estadioRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Estadio no encontrado con id: " + id));
            estadioRepository.delete(estadio);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar estadio", e);
        }
    }

    @Transactional
    public EstadioResponse obtenerEstadioPorId(Long id) {
        try {
            EstadioModel estadio = estadioRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Estadio no encontrado con id: " + id));
            return estadioMapper.mapToEstadioResponse(estadio);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener estadio", e);
        }
    }
}
