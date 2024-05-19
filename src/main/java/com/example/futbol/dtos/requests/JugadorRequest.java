package com.example.futbol.dtos.requests;


import com.example.futbol.models.ContratoModel;
import com.example.futbol.models.EquipoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JugadorRequest {
    private String nombre;
    private String apellido;
    private Long num_camiseta;
    private Integer edad;
    private Integer alturaCm;
    private ContratoModel contrato;
}
