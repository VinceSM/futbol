package com.example.futbol.services;

import com.example.futbol.dtos.requests.EquipoRequest;
import com.example.futbol.dtos.responses.EquipoResponse;
import com.example.futbol.mappers.EquipoMapper;
import com.example.futbol.models.EquipoModel;
import com.example.futbol.repositories.EquipoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private EquipoMapper equipoMapper;

    @Transactional
    public EquipoResponse crearEquipo(EquipoRequest equipoRequest) {
        try {
            EquipoModel equipo = equipoMapper.mapToEquipoModel(equipoRequest);
            EquipoModel equipoGuardado = equipoRepository.save(equipo);
            return equipoMapper.mapToEquipoResponse(equipoGuardado);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear equipo", e);
        }
    }

    @Transactional
    public List<EquipoResponse> listarEquipos() {
        try {
            List<EquipoModel> equipos = equipoRepository.findAll();
            return equipos.stream()
                    .map(equipoMapper::mapToEquipoResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al listar equipos", e);
        }
    }

    @Transactional
    public EquipoResponse modificarEquipo(Long id, EquipoRequest equipoRequest) {
        try {
            EquipoModel equipo = equipoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado con id: " + id));

            equipo.setNombreEquipo(equipoRequest.getNombreEquipo());
            equipo.setApodo(equipoRequest.getApodo());
            equipo.setEstadio(equipoRequest.getEstadio());
            equipo.setPosicion(equipoRequest.getPosicion());
            equipo.setCompeticion(equipoRequest.getCompeticion());

            EquipoModel equipoModificado = equipoRepository.save(equipo);
            return equipoMapper.mapToEquipoResponse(equipoModificado);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al modificar equipo", e);
        }
    }

    @Transactional
    public void eliminarEquipo(Long id) {
        try {
            EquipoModel equipo = equipoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado con id: " + id));
            equipoRepository.delete(equipo);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar equipo", e);
        }
    }

    @Transactional
    public EquipoResponse obtenerEquipoPorId(Long id) {
        try {
            EquipoModel equipo = equipoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado con id: " + id));
            return equipoMapper.mapToEquipoResponse(equipo);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener equipo", e);
        }
    }
}
