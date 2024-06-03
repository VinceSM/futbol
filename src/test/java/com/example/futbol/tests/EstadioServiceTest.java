package com.example.futbol.tests;

import com.example.futbol.dtos.requests.EstadioRequest;
import com.example.futbol.dtos.responses.EstadioResponse;
import com.example.futbol.models.EstadioModel;
import com.example.futbol.services.EstadioService;
import com.example.futbol.repositories.EstadioRepository;
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
class EstadioServiceTest {

    @Mock
    private EstadioRepository estadioRepository;

    @InjectMocks
    private EstadioService estadioService;

    @Test
    void testCrearEstadio() {
        // Arrange
        EstadioRequest estadioRequest = new EstadioRequest();
        EstadioModel estadioModel = new EstadioModel();
        when(estadioRepository.save(any())).thenReturn(estadioModel);

        // Act
        EstadioResponse response = estadioService.crearEstadio(estadioRequest);

        // Assert
        assertEquals(estadioModel.getId(), response.getId());
    }

    @Test
    void testListarEstadios() {
        // Arrange
        EstadioModel estadioModel = new EstadioModel();
        when(estadioRepository.findAll()).thenReturn(Collections.singletonList(estadioModel));

        // Act
        estadioService.listarEstadios();

        // Assert
        verify(estadioRepository, times(1)).findAll();
    }

    @Test
    void testModificarEstadio() {
        // Arrange
        Long id = 1L;
        EstadioRequest estadioRequest = new EstadioRequest();
        estadioRequest.setNombre("Estadio de prueba");
        EstadioModel estadioModel = new EstadioModel();
        estadioModel.setId(id);
        when(estadioRepository.findById(id)).thenReturn(Optional.of(estadioModel));
        when(estadioRepository.save(any())).thenReturn(estadioModel);

        // Act
        EstadioResponse response = estadioService.modificarEstadio(id, estadioRequest);

        // Assert
        assertEquals(estadioModel.getId(), response.getId());
        assertEquals(estadioRequest.getNombre(), response.getNombre());
    }

    @Test
    void testEliminarEstadio_Existente() {
        // Arrange
        Long id = 1L;
        EstadioModel estadioModel = new EstadioModel();
        when(estadioRepository.findById(id)).thenReturn(Optional.of(estadioModel));

        // Act
        estadioService.eliminarEstadio(id);

        // Assert
        verify(estadioRepository, times(1)).delete(estadioModel);
    }

    @Test
    void testEliminarEstadio_NoEncontrado() {
        // Arrange
        Long idInexistente = 1L;
        when(estadioRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> estadioService.eliminarEstadio(idInexistente));
    }

    @Test
    void testObtenerEstadioPorId_Existente() {
        // Arrange
        Long id = 1L;
        EstadioModel estadioModel = new EstadioModel();
        when(estadioRepository.findById(id)).thenReturn(Optional.of(estadioModel));

        // Act
        estadioService.obtenerEstadioPorId(id);

        // Assert
        verify(estadioRepository, times(1)).findById(id);
    }

    @Test
    void testObtenerEstadioPorId_NoEncontrado() {
        // Arrange
        Long idInexistente = 1L;
        when(estadioRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> estadioService.obtenerEstadioPorId(idInexistente));
    }
}

