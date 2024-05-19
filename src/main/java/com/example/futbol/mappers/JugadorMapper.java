package com.example.futbol.mappers;

import com.example.futbol.dtos.requests.JugadorRequest;
import com.example.futbol.dtos.responses.JugadorResponse;
import com.example.futbol.models.ContratoModel;
import com.example.futbol.models.JugadorModel;
import org.springframework.stereotype.Service;

@Service
public class JugadorMapper {
    public JugadorModel mapToJugadorModel(JugadorRequest jugadorRequest) {
        JugadorModel jugador = new JugadorModel();
        jugador.setNombre(jugadorRequest.getNombre());
        jugador.setApellido(jugadorRequest.getApellido());
        jugador.setNum_camiseta(jugadorRequest.getNum_camiseta());
        jugador.setEdad(jugadorRequest.getEdad());
        jugador.setAlturaCm(jugadorRequest.getAlturaCm());
        jugador.setContrato(jugadorRequest.getContrato());
        return jugador;
    }

    public JugadorResponse mapToJugadorResponse(JugadorModel nuevoJugador) {
        JugadorResponse jugadorResponse = new JugadorResponse();
        jugadorResponse.setId(nuevoJugador.getId());
        jugadorResponse.setNombre(nuevoJugador.getNombre());
        jugadorResponse.setApellido(nuevoJugador.getApellido());
        jugadorResponse.setNum_camiseta(nuevoJugador.getNum_camiseta());
        jugadorResponse.setEdad(nuevoJugador.getEdad());
        jugadorResponse.setAlturaCm(nuevoJugador.getAlturaCm());
        jugadorResponse.setContrato(nuevoJugador.getContrato());
        return jugadorResponse;
    }

}

