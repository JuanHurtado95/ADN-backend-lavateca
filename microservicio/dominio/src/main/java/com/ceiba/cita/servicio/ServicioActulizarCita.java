package com.ceiba.cita.servicio;

import com.ceiba.cita.excepcion.ExcepcionCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;

import java.time.LocalDate;

public class ServicioActulizarCita{

    private static final String LA_CITA_NO_EXISTE_EN_EL_SISTEMA = "La cita no existe en el sistema";
    private static final String El_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El usuario no existe en el sistema";

    private final RepositorioCita repositorioCita;
    private final RepositorioUsuario repositorioUsuario;
    private final ValidadorCita validadorCita;

    public ServicioActulizarCita(RepositorioCita repositorioCita, RepositorioUsuario repositorioUsuario) {
        this.repositorioCita = repositorioCita;
        this.repositorioUsuario = repositorioUsuario;
        this.validadorCita = new ValidadorCita(this.repositorioCita);
    }

    public void ejecutar(Cita cita){
        validarExistenciaCita(cita.getPlaca());
        validarExistenciaUsuario(cita.getCedulaUsuario());
        validadorCita.validarActualizarFechaCita(LocalDate.parse(cita.getFecha()));
        this.repositorioCita.actualizar(cita);
    }

    private void validarExistenciaCita(String placa) {
        boolean existe = this.repositorioCita.existe(placa);
        if(!existe){
            throw new ExcepcionCita(LA_CITA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private void validarExistenciaUsuario(String cedula) {
        boolean existe = this.repositorioCita.existeCitaPersona(cedula);
        if(!existe){
            throw new ExcepcionCita(LA_CITA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
