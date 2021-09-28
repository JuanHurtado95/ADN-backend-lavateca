package com.ceiba.cita.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoCita {

    private Long id;
    private String cedulaUsuario;
    private String fecha;
    private String tipoVehiculo;
    private String placa;
    private Double valor;

}
