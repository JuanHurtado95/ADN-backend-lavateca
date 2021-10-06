package com.ceiba.tipo_vehiculo.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoTipoVehiculo {

    private Long id;
    private String nombre;
    private Double precioLavada;

}
