package com.example.futbol.controllers;

import com.example.futbol.dtos.requests.EstadioRequest;
import com.example.futbol.dtos.responses.EstadioResponse;
import com.example.futbol.services.EstadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estadios")
public class EstadioController {

    @Autowired
    private EstadioService estadioService;

    @PostMapping("/crearEstadio")
    public ResponseEntity<EstadioResponse> crearEstadio(@RequestBody EstadioRequest estadioRequest) {
        EstadioResponse nuevoEstadio = estadioService.crearEstadio(estadioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstadio);
    }

    @GetMapping("/listarEstadios")
    public ResponseEntity<List<EstadioResponse>> listarEstadios() {
        List<EstadioResponse> estadios = estadioService.listarEstadios();
        return ResponseEntity.ok(estadios);
    }

    @PutMapping("/modificarEstadio/{id}")
    public ResponseEntity<EstadioResponse> modificarEstadio(@PathVariable Long id, @RequestBody EstadioRequest estadioRequest) {
        EstadioResponse estadioModificado = estadioService.modificarEstadio(id, estadioRequest);
        return ResponseEntity.ok(estadioModificado);
    }

    @DeleteMapping("/eliminarEstadio/{id}")
    public ResponseEntity<String> eliminarEstadio(@PathVariable Long id) {
        estadioService.eliminarEstadio(id);
        return ResponseEntity.ok("Estadio eliminado correctamente");
    }

    @GetMapping("/verEstadio/{id}")
    public ResponseEntity<EstadioResponse> obtenerEstadioPorId(@PathVariable Long id) {
        EstadioResponse estadio = estadioService.obtenerEstadioPorId(id);
        return ResponseEntity.ok(estadio);
    }
}
