package com.ceiba.usuario.modelo.entidad;


import lombok.Getter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarLongitud;
import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Usuario {

    private static final String SE_DEBE_INGRESAR_LA_CEDULA = "Se debe ingresar la cedula";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO = "Se debe ingresar el nombre de usuario";
    private static final String SE_DEBE_INGRESAR_EL_TELEFONO = "Se debe ingresar el telefono";

    private static final String LA_CEDULA_DEBE_SER_NUMERICO = "La Cedula debe ser numerica, no debe contener simbolos, ni espacios";
    private static final String LA_CEDULA_DEBE_SER_POSITIVA = "La Cedula debe ser numerica positiva";
    private static final String EL_NOMBRE_DEBE_SER_TEXTO = "El nombre solo puede contener letas, sin numeros ni simbolos";
    private static final String EL_TELEFONO_DEBE_SER_NUMERICO = "El telefono debe ser numerico, no debe contener simbolos, ni espacios";
    private static final String EL_TELEFONO_DEBE_SER_POSITIVA = "La telefono debe ser numerica positiva";




    private Long id;
    private String cedula;
    private String nombre;
    private String telefono;

    public Usuario(Long id, String cedula, String nombre, String telefono) {
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO);
        validarObligatorio(cedula, SE_DEBE_INGRESAR_LA_CEDULA);
        validarObligatorio(telefono, SE_DEBE_INGRESAR_EL_TELEFONO);

        validarNumerico(cedula, LA_CEDULA_DEBE_SER_POSITIVA);
        validarPositivo(Double.parseDouble(cedula), LA_CEDULA_DEBE_SER_POSITIVA);
        validarNumerico(telefono, LA_CEDULA_DEBE_SER_POSITIVA);
        validarPositivo(Double.parseDouble(telefono), LA_CEDULA_DEBE_SER_POSITIVA);


        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
    }

}
