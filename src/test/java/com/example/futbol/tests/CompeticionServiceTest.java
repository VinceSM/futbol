package com.example.futbol.tests;

import com.example.futbol.dtos.requests.CompeticionRequest;
import com.example.futbol.dtos.responses.CompeticionResponse;
import com.example.futbol.mappers.CompeticionMapper;
import com.example.futbol.models.CompeticionModel;
import com.example.futbol.repositories.CompeticionRepository;
import com.example.futbol.services.CompeticionService;
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
public class CompeticionServiceTest {

    @Mock
    private CompeticionRepository competicionRepository;

    @Mock
    private CompeticionMapper competicionMapper;

    @InjectMocks
    private CompeticionService competicionService;

    @Test
    public void testCrearCompeticion() {
        // Arrange
        CompeticionRequest competicionRequest = new CompeticionRequest();
        competicionRequest.setNombre("Champions League");

        CompeticionModel competicionModel = new CompeticionModel();
        competicionModel.setId(1L);
        competicionModel.setNombre("Champions League");

        CompeticionResponse expectedResponse = new CompeticionResponse();
        expectedResponse.setId(1L);
        expectedResponse.setNombre("Champions League");

        when(competicionMapper.mapToCompeticionModel(competicionRequest)).thenReturn(competicionModel);
        when(competicionRepository.save(competicionModel)).thenReturn(competicionModel);
        when(competicionMapper.mapToCompeticionResponse(competicionModel)).thenReturn(expectedResponse);

        // Act
        CompeticionResponse response = competicionService.crearCompeticion(competicionRequest);

        // Assert
        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getNombre(), response.getNombre());
    }

    @Test
    public void testListarCompeticiones() {
        // Arrange
        List<CompeticionModel> competiciones = new ArrayList<>();
        CompeticionModel competicionModel = new CompeticionModel();
        competicionModel.setId(1L);
        competicionModel.setNombre("Competicion de Prueba");
        competiciones.add(competicionModel);
        when(competicionRepository.findAll()).thenReturn(competiciones);
        when(competicionMapper.mapToCompeticionResponse(any(CompeticionModel.class))).thenReturn(new CompeticionResponse());

        List<CompeticionResponse> response = competicionService.listarCompeticiones();

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testEliminarCompeticion_NoEncontrado() {
        // Arrange
        long idInexistente = 999L;
        when(competicionRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act
        competicionService.eliminarCompeticion(idInexistente);
    }
}
