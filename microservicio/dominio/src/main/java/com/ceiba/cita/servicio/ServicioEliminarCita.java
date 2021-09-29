package com.ceiba.cita.servicio;

import com.ceiba.cita.excepcion.ExcepcionCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;

public class ServicioEliminarCita {

    private static final String LA_CITA_NO_EXISTE_EN_EL_SISTEMA = "La cita no existe en el sistema";

    private final RepositorioCita repositorioCita;

    public ServicioEliminarCita(RepositorioCita repositorioCita){
        this.repositorioCita = repositorioCita;
    }

    public void ejecutar(Long id){
        this.repositorioCita.eliminar(id);
    }
}
