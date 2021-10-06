package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

public class ServicioCrearCita {

    private static final String LA_CITA_YA_EXISTE_EN_EL_SISTEMA = "La cita para el vehiculo ya existe en el sistema";
    private static final String EL_VEHICULO_NO_EXISTE_EN_EL_SISTEMA = "El vehiculo no esta registrado en el sistema";

    private final RepositorioCita repositorioCita;

    public ServicioCrearCita(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public Long ejecutar(Cita cita){
        validarExistenciaCitaPorId(cita);
        validarExistenciaVehiculo(cita);
        return this.repositorioCita.crear(cita);
    }

    private void validarExistenciaCitaPorId(Cita cita){
        boolean existe = this.repositorioCita.existe(cita.getId());
        if(existe){
            throw new ExcepcionValorInvalido(LA_CITA_YA_EXISTE_EN_EL_SISTEMA);
        }
    }

    private void validarExistenciaVehiculo(Cita cita){
        boolean existe = this.repositorioCita.existeVehiculo(cita.getIdVehiculo());
        if(!existe){
            throw new ExcepcionValorInvalido(EL_VEHICULO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
