package com.ceiba.cita.modelo.dto;

import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoCita {

    private Long id;
    private DtoVehiculo vehiculo;
    private LocalDate fecha;

}
