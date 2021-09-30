package com.ceiba.usuario.servicio.testdatabuilder;

//import com.ceiba.usuario.comando;
import java.time.LocalDateTime;
import java.util.UUID;

public class ComandoUsuarioTestDataBuilder {

    private Long id;
    private String cedula;
    private String nombre;
    private String telefono;

    public ComandoUsuarioTestDataBuilder() {
        id=1l;
        cedula="23534";
        nombre = "juan";
        telefono = "23433564";
    }

    public ComandoUsuarioTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    /*
    public ComandoUsuario build() {
        return new ComandoUsuario(id,nombre, clave,fecha);
    }*/

}
