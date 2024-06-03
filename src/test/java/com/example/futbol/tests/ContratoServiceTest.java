package com.example.futbol.tests;

import com.example.futbol.dtos.requests.ContratoRequest;
import com.example.futbol.dtos.responses.ContratoResponse;
import com.example.futbol.models.ContratoModel;
import com.example.futbol.services.ContratoService;
import com.example.futbol.repositories.ContratoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContratoServiceTest {

    @Mock
    private ContratoRepository contratoRepository;

    @InjectMocks
    private ContratoService contratoService;

    @Test
    void testCrearContrato() {
        // Arrange
        ContratoRequest contratoRequest = new ContratoRequest();
        ContratoModel contratoModel = new ContratoModel();
        when(contratoRepository.save(any())).thenReturn(contratoModel);

        // Act
        ContratoResponse response = contratoService.crearContrato(contratoRequest);

        // Assert
        assertEquals(contratoModel.getId(), response.getId());
    }

    @Test
    void testListarContratos() {
        // Arrange
        ContratoModel contratoModel = new ContratoModel();
        when(contratoRepository.findAll()).thenReturn(Collections.singletonList(contratoModel));

        // Act
        contratoService.listarContratos();

        // Assert
        verify(contratoRepository, times(1)).findAll();
    }

    @Test
    void testModificarContrato() {
        // Arrange
        Long id = 1L;
        ContratoRequest contratoRequest = new ContratoRequest();
        contratoRequest.setDescripcion("Contrato de prueba");
        ContratoModel contratoModel = new ContratoModel();
        contratoModel.setId(id);
        when(contratoRepository.findById(id)).thenReturn(Optional.of(contratoModel));
        when(contratoRepository.save(any())).thenReturn(contratoModel);

        // Act
        ContratoResponse response = contratoService.modificarContrato(id, contratoRequest);

        // Assert
        assertEquals(contratoModel.getId(), response.getId());
        assertEquals(contratoRequest.getDescripcion(), response.getDescripcion());
    }

    @Test
    void testEliminarContrato() {
        // Arrange
        Long id = 1L;
        ContratoModel contratoModel = new ContratoModel();
        when(contratoRepository.findById(id)).thenReturn(Optional.of(contratoModel));

        // Act
        contratoService.eliminarContrato(id);

        // Assert
        verify(contratoRepository, times(1)).deleteById(id);
    }

    @Test
    void testObtenerContratoPorId_Existente() {
        // Arrange
        Long id = 1L;
        ContratoModel contratoModel = new ContratoModel();
        when(contratoRepository.findById(id)).thenReturn(Optional.of(contratoModel));

        // Act
        contratoService.obtenerContratoPorId(id);

        // Assert
        verify(contratoRepository, times(1)).findById(id);
    }

    @Test
    void testObtenerContratoPorId_NoEncontrado() {
        // Arrange
        Long idInexistente = 1L;
        when(contratoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> contratoService.obtenerContratoPorId(idInexistente));
    }
}

