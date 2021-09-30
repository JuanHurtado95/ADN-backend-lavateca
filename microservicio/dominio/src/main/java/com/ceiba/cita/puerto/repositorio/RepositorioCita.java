package com.ceiba.cita.puerto.repositorio;

import com.ceiba.cita.modelo.entidad.Cita;

import java.time.LocalDate;

public interface RepositorioCita {

    Long crear(Cita cita);
    void actualizar(Cita cita);
    void eliminar(Long id);
    boolean existe(String placa, String fecha);
    boolean existe(Long id);
    boolean existePersona(String cedula);
    LocalDate fechaCita(Long id);

}
