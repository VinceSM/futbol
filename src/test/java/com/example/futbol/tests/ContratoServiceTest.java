package com.example.futbol.tests;

import com.example.futbol.dtos.requests.ContratoRequest;
import com.example.futbol.dtos.responses.ContratoResponse;
import com.example.futbol.mappers.ContratoMapper;
import com.example.futbol.models.ContratoModel;
import com.example.futbol.repositories.ContratoRepository;
import com.example.futbol.services.ContratoService;
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
public class ContratoServiceTest {

    @Mock
    private ContratoRepository contratoRepository;

    @Mock
    private ContratoMapper contratoMapper;

    @InjectMocks
    private ContratoService contratoService;

    @Test
    public void testCrearContrato() {
        // Arrange
        ContratoRequest contratoRequest = new ContratoRequest();
        contratoRequest.setDuracionAnual(2);

        ContratoModel contratoModel = new ContratoModel();
        contratoModel.setId(1L);
        contratoModel.setDuracionAnual(2);

        ContratoResponse expectedResponse = new ContratoResponse();
        expectedResponse.setId(1L);
        expectedResponse.setDuracionAnual(2);

        when(contratoMapper.mapToContratoModel(contratoRequest)).thenReturn(contratoModel);
        when(contratoRepository.save(contratoModel)).thenReturn(contratoModel);
        when(contratoMapper.mapToContratoResponse(contratoModel)).thenReturn(expectedResponse);

        // Act
        ContratoResponse response = contratoService.crearContrato(contratoRequest);

        // Assert
        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getDuracionAnual(), response.getDuracionAnual());
    }

    @Test
    public void testListarContratos() {
        // Arrange
        List<ContratoModel> contratos = new ArrayList<>();
        ContratoModel contratoModel = new ContratoModel();
        contratoModel.setId(1L);
        contratoModel.setDuracionAnual(2);
        contratos.add(contratoModel);
        when(contratoRepository.findAll()).thenReturn(contratos);
        when(contratoMapper.mapToContratoResponse(any(ContratoModel.class))).thenReturn(new ContratoResponse());

        List<ContratoResponse> response = contratoService.listarContratos();

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testEliminarContrato_NoEncontrado() {
        // Arrange
        long idInexistente = 999L;
        when(contratoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act
        contratoService.eliminarContrato(idInexistente);
    }
}
