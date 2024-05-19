package com.example.futbol.controllers;

import com.example.futbol.dtos.requests.ContratoRequest;
import com.example.futbol.dtos.responses.ContratoResponse;
import com.example.futbol.services.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @PostMapping("/crearContrato")
    public ResponseEntity<ContratoResponse> crearContrato(@RequestBody ContratoRequest contratoRequest) {
        ContratoResponse nuevoContrato = contratoService.crearContrato(contratoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoContrato);
    }

    @GetMapping("/listarContratos")
    public ResponseEntity<List<ContratoResponse>> listarContratos() {
        List<ContratoResponse> contratos = contratoService.listarContratos();
        return ResponseEntity.ok(contratos);
    }

    @PutMapping("/modificarContrato/{id}")
    public ResponseEntity<ContratoResponse> modificarContrato(@PathVariable Long id, @RequestBody ContratoRequest contratoRequest) {
        ContratoResponse contratoModificado = contratoService.modificarContrato(id, contratoRequest);
        return ResponseEntity.ok(contratoModificado);
    }

    @DeleteMapping("/eliminarContrato/{id}")
    public ResponseEntity<String> eliminarContrato(@PathVariable Long id) {
        contratoService.eliminarContrato(id);
        return ResponseEntity.ok("Contrato eliminado correctamente");
    }

    @GetMapping("/verContrato/{id}")
    public ResponseEntity<ContratoResponse> obtenerContratoPorId(@PathVariable Long id) {
        ContratoResponse contrato = contratoService.obtenerContratoPorId(id);
        return ResponseEntity.ok(contrato);
    }
}
