package com.ceiba.cita.servicio;

import com.ceiba.cita.excepcion.ExcepcionCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.modelo.entidad.Cita;

public class ServicioCrearCita {

    private static final String LA_CITA_YA_EXISTE_EN_EL_SISTEMA = "La cita para el vehiculo ya existe en el sistema";

    private final RepositorioCita repositorioCita;

    public ServicioCrearCita(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public Long ejecutar(Cita cita){
        validarExistenciaCitaPorPlaca(cita);
        return this.repositorioCita.crear(cita);
    }

    private void validarExistenciaCitaPorPlaca(Cita cita){
        boolean existe = this.repositorioCita.existe(cita.getPlaca());
        if(existe){
            throw new ExcepcionCita(LA_CITA_YA_EXISTE_EN_EL_SISTEMA);
        }
    }

}
