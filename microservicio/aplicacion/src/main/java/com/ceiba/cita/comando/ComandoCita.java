package com.ceiba.cita.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCita {

    private Long id;
    private String cedulaUsuario;
    private String fecha;
    private String tipoVehiculo;
    private String placa;
    private Double valor;

}
