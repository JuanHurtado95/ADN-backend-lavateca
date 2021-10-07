package com.ceiba.vehiculo.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoVehiculo {

    private Long id;
    private Long idUsuario;
    private Long idTipoVehiculo;
    private String placa;

}
