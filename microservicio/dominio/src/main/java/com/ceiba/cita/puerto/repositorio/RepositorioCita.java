package com.ceiba.cita.puerto.repositorio;

import com.ceiba.cita.modelo.entidad.Cita;

public interface RepositorioCita {

    Long crear(Cita cita);
    void actualizar(Cita cita);
    void eliminar(Long id);
    boolean existe(String placa);
    boolean existeCitaPersona(String cedula);

}
