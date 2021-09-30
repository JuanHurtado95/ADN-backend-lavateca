package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;

public class ServicioCrearCita {

    private static final String LA_CITA_YA_EXISTE_EN_EL_SISTEMA = "La cita para el vehiculo ya existe en el sistema";
    private static final String EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El usuario no esta registrado en el sistema";

    private final RepositorioCita repositorioCita;
    private final RepositorioUsuario repositorioUsuario;

    public ServicioCrearCita(RepositorioCita repositorioCita, RepositorioUsuario repositorioUsuario) {
        this.repositorioCita = repositorioCita;
        this.repositorioUsuario = repositorioUsuario;
    }

    public Long ejecutar(Cita cita){
        validarExistenciaCitaPorPlacaFecha(cita);
        validarExistenciaUsuario(cita);
        return this.repositorioCita.crear(cita);
    }

    private void validarExistenciaCitaPorPlacaFecha(Cita cita){
        boolean existe = this.repositorioCita.existe(cita.getPlaca(), cita.getFecha());
        if(existe){
            throw new ExcepcionValorInvalido(LA_CITA_YA_EXISTE_EN_EL_SISTEMA);
        }
    }

    private void validarExistenciaUsuario(Cita cita) {
        boolean existe = this.repositorioCita.existePersona(cita.getCedulaUsuario());
        if(!existe){
            throw new ExcepcionValorInvalido(EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

}
