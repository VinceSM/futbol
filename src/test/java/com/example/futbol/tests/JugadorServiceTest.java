package com.example.futbol.tests;

import com.example.futbol.dtos.requests.JugadorRequest;
import com.example.futbol.dtos.responses.JugadorResponse;
import com.example.futbol.mappers.JugadorMapper;
import com.example.futbol.models.JugadorModel;
import com.example.futbol.repositories.JugadorRepository;
import com.example.futbol.services.JugadorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Service
@RunWith(MockitoJUnitRunner.class)
public class JugadorServiceTest {

    @Mock
    private JugadorRepository jugadorRepository;

    @Mock
    private JugadorMapper jugadorMapper;

    @InjectMocks
    private JugadorService jugadorService;

    @Test
    public void testCrearJugador() {
        // Arrange
        JugadorRequest jugadorRequest = new JugadorRequest();
        jugadorRequest.setNombre("Lionel Messi");

        JugadorModel jugadorModel = new JugadorModel();
        jugadorModel.setId(1L);
        jugadorModel.setNombre("Lionel Messi");

        JugadorResponse expectedResponse = new JugadorResponse();
        expectedResponse.setId(1L);
        expectedResponse.setNombre("Lionel Messi");

        when(jugadorMapper.mapToJugadorModel(jugadorRequest)).thenReturn(jugadorModel);
        when(jugadorRepository.save(jugadorModel)).thenReturn(jugadorModel);
        when(jugadorMapper.mapToJugadorResponse(jugadorModel)).thenReturn(expectedResponse);

        // Act
        JugadorResponse response = jugadorService.crearJugador(jugadorRequest);

        // Assert
        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getNombre(), response.getNombre());
    }

    @Test
    public void testListarJugadores() {
        // Arrange
        List<JugadorModel> jugadores = new ArrayList<>();
        JugadorModel jugadorModel = new JugadorModel();
        jugadorModel.setId(1L);
        jugadorModel.setNombre("Jugador de Prueba");
        jugadores.add(jugadorModel);
        when(jugadorRepository.findAll()).thenReturn(jugadores);
        when(jugadorMapper.mapToJugadorResponse(any(JugadorModel.class))).thenReturn(new JugadorResponse());

        List<JugadorResponse> response = jugadorService.listarJugadores();

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testEliminarJugador_NoEncontrado() {
        // Arrange
        long idInexistente = 999L;
        when(jugadorRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act
        jugadorService.eliminarJugador(idInexistente);
    }
}
