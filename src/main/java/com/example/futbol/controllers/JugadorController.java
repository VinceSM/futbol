package com.example.futbol.controllers;

import com.example.futbol.dtos.requests.JugadorRequest;
import com.example.futbol.dtos.responses.JugadorResponse;
import com.example.futbol.services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    @PostMapping("/crearJugador")
    public ResponseEntity<JugadorResponse> crearJugador(@RequestBody JugadorRequest jugadorRequest) {
        JugadorResponse nuevoJugador = jugadorService.crearJugador(jugadorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoJugador);
    }

    @GetMapping("/listarJugadores")
    public ResponseEntity<List<JugadorResponse>> listarJugadores() {
        List<JugadorResponse> jugadores = jugadorService.listarJugadores();
        return ResponseEntity.ok(jugadores);
    }

    @PutMapping("/modificarJugador/{id}")
    public ResponseEntity<JugadorResponse> modificarJugador(@PathVariable Long id, @RequestBody JugadorRequest jugadorRequest) {
        JugadorResponse jugadorModificado = jugadorService.modificarJugador(id, jugadorRequest);
        return ResponseEntity.ok(jugadorModificado);
    }

    @DeleteMapping("/eliminarJugador/{id}")
    public ResponseEntity<String> eliminarJugador(@PathVariable Long id) {
        jugadorService.eliminarJugador(id);
        return ResponseEntity.ok("Jugador eliminado correctamente");
    }

    @GetMapping("/verJugador/{id}")
    public ResponseEntity<JugadorResponse> obtenerJugadorPorId(@PathVariable Long id) {
        JugadorResponse jugador = jugadorService.obtenerJugadorPorId(id);
        return ResponseEntity.ok(jugador);
    }
}
