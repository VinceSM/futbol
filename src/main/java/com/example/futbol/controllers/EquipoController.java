package com.example.futbol.controllers;

import com.example.futbol.dtos.requests.EquipoRequest;
import com.example.futbol.dtos.responses.EquipoResponse;
import com.example.futbol.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @PostMapping("/crearEquipo")
    public ResponseEntity<EquipoResponse> crearEquipo(@RequestBody EquipoRequest equipoRequest) {
        EquipoResponse nuevoEquipo = equipoService.crearEquipo(equipoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEquipo);
    }

    @GetMapping("/listarEquipos")
    public ResponseEntity<List<EquipoResponse>> listarEquipos() {
        List<EquipoResponse> equipos = equipoService.listarEquipos();
        return ResponseEntity.ok(equipos);
    }

    @PutMapping("/modificarEquipo/{id}")
    public ResponseEntity<EquipoResponse> modificarEquipo(@PathVariable Long id, @RequestBody EquipoRequest equipoRequest) {
        EquipoResponse equipoModificado = equipoService.modificarEquipo(id, equipoRequest);
        return ResponseEntity.ok(equipoModificado);
    }

    @DeleteMapping("/eliminarEquipo/{id}")
    public ResponseEntity<String> eliminarEquipo(@PathVariable Long id) {
        equipoService.eliminarEquipo(id);
        return ResponseEntity.ok("Equipo eliminado correctamente");
    }

    @GetMapping("/verEquipo/{id}")
    public ResponseEntity<EquipoResponse> obtenerEquipoPorId(@PathVariable Long id) {
        EquipoResponse equipo = equipoService.obtenerEquipoPorId(id);
        return ResponseEntity.ok(equipo);
    }
}
