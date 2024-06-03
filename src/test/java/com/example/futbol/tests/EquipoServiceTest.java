package com.example.futbol.tests;

import com.example.futbol.dtos.requests.EquipoRequest;
import com.example.futbol.dtos.responses.EquipoResponse;
import com.example.futbol.mappers.EquipoMapper;
import com.example.futbol.models.EquipoModel;
import com.example.futbol.repositories.EquipoRepository;
import com.example.futbol.services.EquipoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Service
@RunWith(MockitoJUnitRunner.class)
public class EquipoServiceTest {

    @Mock
    private EquipoRepository equipoRepository;

    @Mock
    private EquipoMapper equipoMapper;

    @InjectMocks
    private EquipoService equipoService;

    @Mock
    private EquipoRequest equipoRequest;

    @Test
    public void testCrearEquipo() {
        // Arrange
        EquipoRequest equipoRequest = new EquipoRequest();
        equipoRequest.setNombreEquipo("FC Barcelona");
        equipoRequest.setApodo("Los Culés");

        EquipoModel equipoModel = new EquipoModel();
        equipoModel.setId(1L);
        equipoModel.setNombreEquipo("FC Barcelona");
        equipoModel.setApodo("Los Culés");

        EquipoResponse expectedResponse = new EquipoResponse();
        expectedResponse.setId(1L);
        expectedResponse.setNombreEquipo("FC Barcelona");
        expectedResponse.setApodo("Los Culés");

        when(equipoMapper.mapToEquipoModel(equipoRequest)).thenReturn(equipoModel);
        when(equipoRepository.save(equipoModel)).thenReturn(equipoModel);
        when(equipoMapper.mapToEquipoResponse(equipoModel)).thenReturn(expectedResponse);

        // Act
        EquipoResponse response = equipoService.crearEquipo(equipoRequest);

        // Assert
        assertNotNull(response);
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getNombreEquipo(), response.getNombreEquipo());
        assertEquals(expectedResponse.getApodo(), response.getApodo());
    }


    @Test
    public void testListarEquipos() {
        // Arrange
        List<EquipoModel> equipos = new ArrayList<>();
        EquipoModel equipoModel = new EquipoModel();
        equipoModel.setId(1L);
        equipoModel.setNombreEquipo("Equipo de Prueba");
        equipos.add(equipoModel);
        when(equipoRepository.findAll()).thenReturn(equipos);
        when(equipoMapper.mapToEquipoResponse(any(EquipoModel.class))).thenReturn(new EquipoResponse());

        List<EquipoResponse> response = equipoService.listarEquipos();

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testEliminarEquipo_NoEncontrado() {

        long idInexistente = 999L;
        when(equipoRepository.findById(idInexistente)).thenReturn(java.util.Optional.empty());

        equipoService.eliminarEquipo(idInexistente);

    }
}
