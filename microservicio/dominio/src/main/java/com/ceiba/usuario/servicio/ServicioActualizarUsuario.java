package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;

public class ServicioActualizarUsuario {

    private static final String EL_USUARIO_YA_EXISTE_EN_EL_SISTEMA = "El usuario ya existe en el sistema";
    private static final String EL_ID_NO_EXISTE_EN_EL_SISTEMA = "El id no existe en el sistema";

    private final RepositorioUsuario repositorioUsuario;

    public ServicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public void ejecutar(Usuario usuario) {
        validarExistenciaId(usuario);
        validarExistenciaPrevia(usuario);
        this.repositorioUsuario.actualizar(usuario);
    }

    private void validarExistenciaId(Usuario usuario) {
        boolean existe = this.repositorioUsuario.existeId(usuario.getId());
        if(!existe) {
            throw new ExcepcionDuplicidad(EL_ID_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private void validarExistenciaPrevia(Usuario usuario) {
        boolean existe = this.repositorioUsuario.existe(usuario.getCedula());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_USUARIO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
