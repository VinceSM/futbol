package com.example.futbol.services;

import com.example.futbol.dtos.requests.JugadorRequest;
import com.example.futbol.dtos.responses.JugadorResponse;
import com.example.futbol.mappers.JugadorMapper;
import com.example.futbol.models.JugadorModel;
import com.example.futbol.repositories.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private JugadorMapper jugadorMapper;

    @Transactional
    public JugadorResponse crearJugador(JugadorRequest jugadorRequest) {
        try {
            JugadorModel jugador = jugadorMapper.mapToJugadorModel(jugadorRequest);
            JugadorModel nuevoJugador = jugadorRepository.save(jugador);
            return jugadorMapper.mapToJugadorResponse(nuevoJugador);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear jugador", e);
        }
    }

    @Transactional(readOnly = true)
    public List<JugadorResponse> listarJugadores() {
        try {
            List<JugadorModel> jugadores = jugadorRepository.findAll();
            return jugadores.stream()
                    .map(jugadorMapper::mapToJugadorResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al listar jugadores", e);
        }
    }

    @Transactional
    public JugadorResponse modificarJugador(Long id, JugadorRequest jugadorRequest) {
        try {
            JugadorModel jugador = jugadorRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado con id: " + id));

            jugador.setNombre(jugadorRequest.getNombre());
            jugador.setApellido(jugadorRequest.getApellido());
            jugador.setNum_camiseta(jugadorRequest.getNum_camiseta());
            jugador.setEdad(jugadorRequest.getEdad());
            jugador.setAlturaCm(jugadorRequest.getAlturaCm());

            JugadorModel jugadorModificado = jugadorRepository.save(jugador);
            return jugadorMapper.mapToJugadorResponse(jugadorModificado);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al modificar jugador", e);
        }
    }

    @Transactional
    public void eliminarJugador(Long id) {
        try {
            JugadorModel jugador = jugadorRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado con id: " + id));
            jugadorRepository.delete(jugador);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar jugador", e);
        }
    }

    @Transactional
    public JugadorResponse obtenerJugadorPorId(Long id) {
        try {
            JugadorModel jugador = jugadorRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado con id: " + id));
            return jugadorMapper.mapToJugadorResponse(jugador);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener jugador", e);
        }
    }
}
