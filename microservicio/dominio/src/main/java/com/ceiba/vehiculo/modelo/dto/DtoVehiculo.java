package com.ceiba.vehiculo.modelo.dto;

import com.ceiba.tipo_vehiculo.modelo.dto.DtoTipoVehiculo;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoVehiculo {

    private Long id;
    private DtoUsuario usuario;
    private DtoTipoVehiculo tipoVehiculo;
    private String placa;
}
