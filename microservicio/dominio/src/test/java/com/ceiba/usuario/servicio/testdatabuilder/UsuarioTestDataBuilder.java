package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.modelo.entidad.Usuario;

import java.time.LocalDateTime;

public class UsuarioTestDataBuilder {

    private Long id;
    private String cedula;
    private String nombre;
    private String telefono;

    public UsuarioTestDataBuilder() {
        cedula = "23434456";
        nombre = "juan";
        telefono = "23489252";
    }

    public UsuarioTestDataBuilder conCedula(String cedula) {
        this.cedula = cedula;
        return this;
    }

    public UsuarioTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public Usuario build() {
        return new Usuario(id, cedula, nombre, telefono);
    }
}
