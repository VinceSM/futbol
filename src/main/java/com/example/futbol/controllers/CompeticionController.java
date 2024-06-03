package com.example.futbol.controllers;

import com.example.futbol.dtos.requests.CompeticionRequest;
import com.example.futbol.dtos.responses.CompeticionResponse;
import com.example.futbol.services.CompeticionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competiciones")
public class CompeticionController {

    @Autowired
    private CompeticionService competicionService;

    @PostMapping("/crearCompeticion")
    public ResponseEntity<CompeticionResponse> crearCompeticion(@RequestBody CompeticionRequest competicionRequest) {
        CompeticionResponse nuevaCompeticion = competicionService.crearCompeticion(competicionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCompeticion);
    }

    @GetMapping("/listarCompeticiones")
    public ResponseEntity<List<CompeticionResponse>> listarCompeticiones() {
        List<CompeticionResponse> competiciones = competicionService.listarCompeticiones();
        return ResponseEntity.ok(competiciones);
    }

    @PutMapping("/modificarCompeticion/{id}")
    public ResponseEntity<CompeticionResponse> modificarCompeticion(@PathVariable Long id, @RequestBody CompeticionRequest competicionRequest) {
        CompeticionResponse competicionModificada = competicionService.modificarCompeticion(id, competicionRequest);
        return ResponseEntity.ok(competicionModificada);
    }

    @DeleteMapping("/eliminarCompeticion/{id}")
    public ResponseEntity<String> eliminarCompeticion(@PathVariable Long id) {
        competicionService.eliminarCompeticion(id);
        return ResponseEntity.ok("Competición eliminada correctamente");
    }

    @GetMapping("/verCompeticion/{id}")
    public ResponseEntity<CompeticionResponse> obtenerCompeticionPorId(@PathVariable Long id) {
        CompeticionResponse competicion = competicionService.obtenerCompeticionPorId(id);
        return ResponseEntity.ok(competicion);
    }
}
