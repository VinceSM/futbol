package com.example.futbol.tests;

import com.example.futbol.dtos.requests.CompeticionRequest;
import com.example.futbol.dtos.responses.CompeticionResponse;
import com.example.futbol.models.CompeticionModel;
import com.example.futbol.services.CompeticionService;
import com.example.futbol.repositories.CompeticionRepository;
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
class CompeticionServiceTest {

    @Mock
    private CompeticionRepository competicionRepository;

    @InjectMocks
    private CompeticionService competicionService;

    @Test
    void testCrearCompeticion() {
        // Arrange
        CompeticionRequest competicionRequest = new CompeticionRequest();
        CompeticionModel competicionModel = new CompeticionModel();
        when(competicionRepository.save(any())).thenReturn(competicionModel);

        // Act
        CompeticionResponse response = competicionService.crearCompeticion(competicionRequest);

        // Assert
        assertEquals(competicionModel.getId(), response.getId());
    }

    @Test
    void testListarCompeticiones() {
        // Arrange
        CompeticionModel competicionModel = new CompeticionModel();
        when(competicionRepository.findAll()).thenReturn(Collections.singletonList(competicionModel));

        // Act
        competicionService.listarCompeticiones();

        // Assert
        verify(competicionRepository, times(1)).findAll();
    }

    @Test
    void testModificarCompeticion() {
        // Arrange
        Long id = 1L;
        CompeticionRequest competicionRequest = new CompeticionRequest();
        competicionRequest.setTipo("Liga");
        competicionRequest.setNombre("La Liga");
        CompeticionModel competicionModel = new CompeticionModel();
        competicionModel.setId(id);
        when(competicionRepository.findById(id)).thenReturn(Optional.of(competicionModel));
        when(competicionRepository.save(any())).thenReturn(competicionModel);

        // Act
        CompeticionResponse response = competicionService.modificarCompeticion(id, competicionRequest);

        // Assert
        assertEquals(competicionModel.getId(), response.getId());
        assertEquals(competicionRequest.getTipo(), response.getTipo());
        assertEquals(competicionRequest.getNombre(), response.getNombre());
    }

    @Test
    void testEliminarCompeticion() {
        // Arrange
        Long id = 1L;
        CompeticionModel competicionModel = new CompeticionModel();
        when(competicionRepository.findById(id)).thenReturn(Optional.of(competicionModel));

        // Act
        competicionService.eliminarCompeticion(id);

        // Assert
        verify(competicionRepository, times(1)).deleteById(id);
    }

    @Test
    void testObtenerCompeticionPorId_Existente() {
        // Arrange
        Long id = 1L;
        CompeticionModel competicionModel = new CompeticionModel();
        when(competicionRepository.findById(id)).thenReturn(Optional.of(competicionModel));

        // Act
        competicionService.obtenerCompeticionPorId(id);

        // Assert
        verify(competicionRepository, times(1)).findById(id);
    }

    @Test
    void testObtenerCompeticionPorId_NoEncontrado() {
        // Arrange
        Long idInexistente = 1L;
        when(competicionRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> competicionService.obtenerCompeticionPorId(idInexistente));
    }
}

