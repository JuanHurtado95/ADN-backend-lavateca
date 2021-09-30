package com.ceiba.cita.servicio;

import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorCita {

    private static final String NO_PUEDE_ACTUALIZAR_LA_FECHA_DE_LA_CITA = "No puede reprogramar la cita un dia antes a la fecha";
    private static final String LA_CITA_ESTA_VENCIDA = "No puede reprogramar una cita vencida";
    private static final int NUMERO_DE_DIAS_NO_ACTUALIZACION = 1;

    private final RepositorioCita repositorioCita;

    public ValidadorCita(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public void validarActualizarFechaCita(LocalDate fechaCita){
        LocalDate fechaActual = LocalDate.now();
        if(ChronoUnit.DAYS.between(fechaActual, fechaCita) == NUMERO_DE_DIAS_NO_ACTUALIZACION){
            throw new ExcepcionValorInvalido(NO_PUEDE_ACTUALIZAR_LA_FECHA_DE_LA_CITA);
        }
        if(ChronoUnit.DAYS.between(fechaActual, fechaCita) < NUMERO_DE_DIAS_NO_ACTUALIZACION){
            throw new ExcepcionValorInvalido(LA_CITA_ESTA_VENCIDA);
        }
    }

    private static void validarFormatoFecha(String fecha, String mensaje) {

        Pattern formatoFecha = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher servicioFormatoFecha = formatoFecha.matcher(fecha);
        if (!servicioFormatoFecha.matches()) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }
}
