package com.example.futbol.tests;

import com.example.futbol.dtos.requests.JugadorRequest;
import com.example.futbol.dtos.responses.JugadorResponse;
import com.example.futbol.models.JugadorModel;
import com.example.futbol.services.JugadorService;
import com.example.futbol.repositories.JugadorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JugadorServiceTest {

    @Mock
    private JugadorRepository jugadorRepository;

    @InjectMocks
    private JugadorService jugadorService;

    @Test
    void testCrearJugador() {
        // Arrange
        JugadorRequest jugadorRequest = new JugadorRequest();
        JugadorModel jugadorModel = new JugadorModel();
        when(jugadorRepository.save(any())).thenReturn(jugadorModel);

        // Act
        JugadorResponse response = jugadorService.crearJugador(jugadorRequest);

        // Assert
        assertEquals(jugadorModel.getId(), response.getId());
    }

    @Test
    void testListarJugadores() {
        // Arrange
        JugadorModel jugadorModel = new JugadorModel();
        when(jugadorRepository.findAll()).thenReturn(Collections.singletonList(jugadorModel));

        // Act
        jugadorService.listarJugadores();

        // Assert
        verify(jugadorRepository, times(1)).findAll();
    }

    @Test
    void testModificarJugador() {
        // Arrange
        Long id = 1L;
        JugadorRequest jugadorRequest = new JugadorRequest();
        jugadorRequest.setNombre("Jugador de prueba");
        JugadorModel jugadorModel = new JugadorModel();
        jugadorModel.setId(id);
        when(jugadorRepository.findById(id)).thenReturn(Optional.of(jugadorModel));
        when(jugadorRepository.save(any())).thenReturn(jugadorModel);

        // Act
        JugadorResponse response = jugadorService.modificarJugador(id, jugadorRequest);

        // Assert
        assertEquals(jugadorModel.getId(), response.getId());
        assertEquals(jugadorRequest.getNombre(), response.getNombre());
    }

    @Test
    void testEliminarJugador_Existente() {
        // Arrange
        Long id = 1L;
        JugadorModel jugadorModel = new JugadorModel();
        when(jugadorRepository.findById(id)).thenReturn(Optional.of(jugadorModel));

        // Act
        jugadorService.eliminarJugador(id);

        // Assert
        verify(jugadorRepository, times(1)).delete(jugadorModel);
    }

    @Test
    void testEliminarJugador_NoEncontrado() {
        // Arrange
        Long idInexistente = 1L;
        when(jugadorRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> jugadorService.eliminarJugador(idInexistente));
    }

    @Test
    void testObtenerJugadorPorId_Existente() {
        // Arrange
        Long id = 1L;
        JugadorModel jugadorModel = new JugadorModel();
        when(jugadorRepository.findById(id)).thenReturn(Optional.of(jugadorModel));

        // Act
        jugadorService.obtenerJugadorPorId(id);

        // Assert
        verify(jugadorRepository, times(1)).findById(id);
    }

    @Test
    void testObtenerJugadorPorId_NoEncontrado() {
        // Arrange
        Long idInexistente = 1L;
        when(jugadorRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> jugadorService.obtenerJugadorPorId(idInexistente));
    }
}
