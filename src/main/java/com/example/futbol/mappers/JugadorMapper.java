package com.example.futbol.mappers;

import com.example.futbol.dtos.requests.JugadorRequest;
import com.example.futbol.dtos.responses.JugadorResponse;
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
        return jugador;
    }

    public JugadorResponse mapToJugadorResponse(JugadorModel nuevoActor) {
        JugadorResponse jugadorResponse = new JugadorResponse();
        jugadorResponse.setId(nuevoActor.getId());
        jugadorResponse.setNombre(nuevoActor.getNombre());
        jugadorResponse.setApellido(nuevoActor.getApellido());
        jugadorResponse.setNum_camiseta(nuevoActor.getNum_camiseta());
        jugadorResponse.setEdad(nuevoActor.getEdad());
        jugadorResponse.setAlturaCm(nuevoActor.getAlturaCm());
        return jugadorResponse;
    }

}

