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
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EquipoServiceTest {

    @Mock
    private EquipoRepository equipoRepository;

    @Mock
    private EquipoMapper equipoMapper;

    @InjectMocks
    private EquipoService equipoService;

    @Test
    public void testCrearEquipo() {
        // Arrange
        EquipoRequest request = new EquipoRequest();
        request.setNombreEquipo("Equipo de Prueba");
        EquipoModel equipoModel = new EquipoModel();
        equipoModel.setId(1L);
        equipoModel.setNombreEquipo("Equipo de Prueba");
        when(equipoMapper.mapToEquipoModel(request)).thenReturn(equipoModel);
        when(equipoRepository.save(any(EquipoModel.class))).thenReturn(equipoModel);

        // Act
        EquipoResponse response = equipoService.crearEquipo(request);

        // Assert
        assertNotNull(response);
        assertEquals(1L, (long) response.getId());
        assertEquals("Equipo de Prueba", response.getNombreEquipo());
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
