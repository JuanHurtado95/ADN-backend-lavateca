package com.ceiba.cita.servicio;

import com.ceiba.cita.excepcion.ExcepcionCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ValidadorCita {

    private static final String NO_PUEDE_ACTUALIZAR_LA_FECHA_DE_LA_CITA = "No puede reprogramar la cita un dia antes a la fecha";
    private static final int NUMERO_DE_DIAS_NO_ACTUALIZACION = 1;

    private final RepositorioCita repositorioCita;


    public ValidadorCita(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public void validarActualizarFechaCita(LocalDate fechaCita){
        LocalDate fechaActual = LocalDate.now();
        if(ChronoUnit.DAYS.between(fechaCita, fechaActual) <= NUMERO_DE_DIAS_NO_ACTUALIZACION){
            throw new ExcepcionCita(NO_PUEDE_ACTUALIZAR_LA_FECHA_DE_LA_CITA);
        }
    }
}
