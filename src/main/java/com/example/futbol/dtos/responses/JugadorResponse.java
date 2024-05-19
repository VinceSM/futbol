package com.example.futbol.dtos.responses;

import com.example.futbol.models.ContratoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JugadorResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private Long num_camiseta;
    private Integer edad;
    private Integer alturaCm;
    private ContratoModel contrato;

}
