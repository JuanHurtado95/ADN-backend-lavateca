package com.ceiba.usuario.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class ServicioActualizarUsuarioTest {

    @Mock
    private RepositorioUsuario repositorioUsuario;

    @InjectMocks
    private ServicioActualizarUsuario servicioActualizarUsuario;

    public ServicioActualizarUsuarioTest() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deberia validar la existencia previa del usuario")
    void deberiaValidarLaExistenciaPreviaDelUsuario() {
        // arrange
        Usuario usuario = new UsuarioTestDataBuilder().conId(5L).conCedula("7634535").build();
        Mockito.when(repositorioUsuario.existe(Mockito.anyString())).thenReturn(true);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarUsuario.ejecutar(usuario), ExcepcionDuplicidad.class,"El usuario ya existe en el sistema");
    }

    @Test
    @DisplayName("Deberia validar actualizacion del usuario")
    void ValidarActualizacionUsuarioTest() {
        // arrange
        Usuario usuario = new UsuarioTestDataBuilder().conCedula("76534535").build();
        Mockito.when(repositorioUsuario.existe(usuario.getCedula())).thenReturn(false);
        // act
        servicioActualizarUsuario.ejecutar(usuario);
        // assert
        Mockito.verify(repositorioUsuario).actualizar(usuario);
    }

}
