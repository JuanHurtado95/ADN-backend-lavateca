package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ServicioActulizarCita{

    private static final String LA_CITA_NO_EXISTE_EN_EL_SISTEMA = "La cita no existe en el sistema";
    private static final String NO_PUEDE_ACTUALIZAR_LA_FECHA_DE_LA_CITA = "No puede reprogramar la cita un dia antes a la fecha";
    private static final String LA_CITA_ESTA_VENCIDA = "No puede reprogramar una cita vencida";
    private static final int NUMERO_DE_DIAS_NO_ACTUALIZACION = 1;

    private final RepositorioCita repositorioCita;

    public ServicioActulizarCita(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public void ejecutar(Cita cita){
        validarExistenciaCita(cita);
        validarActualizarFechaCita(fechaCita(cita.getId()));
        this.repositorioCita.actualizar(cita);
    }

    private void validarExistenciaCita(Cita cita) {
        boolean existe = this.repositorioCita.existe(cita.getId());
        if(!existe){
            throw new ExcepcionValorInvalido(LA_CITA_NO_EXISTE_EN_EL_SISTEMA);
        }
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

    private LocalDate fechaCita(Long id){
        return this.repositorioCita.fechaCita(id);
    }
}
