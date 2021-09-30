package com.ceiba.cita.servicio;

import com.ceiba.cita.excepcion.ExcepcionCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;

import java.time.LocalDate;

public class ServicioActulizarCita{

    private static final String LA_CITA_NO_EXISTE_EN_EL_SISTEMA = "La cita no existe en el sistema";

    private final RepositorioCita repositorioCita;
    private final RepositorioUsuario repositorioUsuario;
    private final ValidadorCita validadorCita;

    public ServicioActulizarCita(RepositorioCita repositorioCita, RepositorioUsuario repositorioUsuario) {
        this.repositorioCita = repositorioCita;
        this.repositorioUsuario = repositorioUsuario;
        this.validadorCita = new ValidadorCita(this.repositorioCita);
    }

    public void ejecutar(Cita cita){
        validarExistenciaCita(cita);
        validadorCita.validarActualizarFechaCita(fechaCita(cita.getId()));
        this.repositorioCita.actualizar(cita);
    }

    private void validarExistenciaCita(Cita cita) {
        boolean existe = this.repositorioCita.existe(cita.getId());
        if(!existe){
            throw new ExcepcionCita(LA_CITA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private LocalDate fechaCita(Long id){
        LocalDate fechaCita = this.repositorioCita.fechaCita(id);
        return fechaCita;
    }
}
