package com.example.futbol.tests;

import com.example.futbol.dtos.requests.EstadioRequest;
import com.example.futbol.dtos.responses.EstadioResponse;
import com.example.futbol.mappers.EstadioMapper;
import com.example.futbol.models.EstadioModel;
import com.example.futbol.repositories.EstadioRepository;
import com.example.futbol.services.EstadioService;
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
public class EstadioServiceTest {

    @Mock
    private EstadioRepository estadioRepository;

    @Mock
    private EstadioMapper estadioMapper;

    @InjectMocks
    private EstadioService estadioService;

    @Test
    public void testCrearEstadio() {
        // Arrange
        EstadioRequest estadioRequest = new EstadioRequest();
        estadioRequest.setNombre("Camp Nou");

        EstadioModel estadioModel = new EstadioModel();
        estadioModel.setId(1L);
        estadioModel.setNombre("Camp Nou");

        EstadioResponse expectedResponse = new EstadioResponse();
        expectedResponse.setId(1L);
        expectedResponse.setNombre("Camp Nou");

        when(estadioMapper.mapToEstadioModel(estadioRequest)).thenReturn(estadioModel);
        when(estadioRepository.save(estadioModel)).thenReturn(estadioModel);
        when(estadioMapper.mapToEstadioResponse(estadioModel)).thenReturn(expectedResponse);

        // Act
        EstadioResponse response = estadioService.crearEstadio(estadioRequest);

        // Assert
        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getNombre(), response.getNombre());
    }

    @Test
    public void testListarEstadios() {
        // Arrange
        List<EstadioModel> estadios = new ArrayList<>();
        EstadioModel estadioModel = new EstadioModel();
        estadioModel.setId(1L);
        estadioModel.setNombre("Estadio de Prueba");
        estadios.add(estadioModel);
        when(estadioRepository.findAll()).thenReturn(estadios);
        when(estadioMapper.mapToEstadioResponse(any(EstadioModel.class))).thenReturn(new EstadioResponse());

        List<EstadioResponse> response = estadioService.listarEstadios();

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testEliminarEstadio_NoEncontrado() {
        // Arrange
        long idInexistente = 999L;
        when(estadioRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act
        estadioService.eliminarEstadio(idInexistente);
    }
}
