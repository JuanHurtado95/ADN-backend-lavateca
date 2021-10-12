package com.ceiba.usuario.servicio.testdatabuilder;

//import com.ceiba.usuario.comando;
import com.ceiba.usuario.comando.ComandoUsuario;

import java.time.LocalDateTime;
import java.util.UUID;

public class ComandoUsuarioTestDataBuilder {

    private Long id;
    private String cedula;
    private String nombre;
    private String telefono;

    public ComandoUsuarioTestDataBuilder() {
        id=1l;
        cedula="2357834";
        nombre = "juan";
        telefono = "23433564";
    }

    public ComandoUsuarioTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ComandoUsuario build() {
        return new ComandoUsuario(id,cedula, nombre, telefono);
    }

}
