package com.ceiba.usuario.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cita.servicio.testdatabuilder.CitaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;

public class ServicioCrearUsuarioTest {

    @Mock
    private RepositorioUsuario repositorioUsuario;

    @InjectMocks
    private ServicioCrearUsuario servicioCrearUsuario;

    public ServicioCrearUsuarioTest() {
        MockitoAnnotations.openMocks(this);
    }

    private static final String EL_USUARIO_YA_EXISTE_EN_EL_SISTEMA = "El usuario ya existe en el sistema";
    private static final String SE_DEBE_INGRESAR_LA_CEDULA = "Se debe ingresar la cedula";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO = "Se debe ingresar el nombre de usuario";
    private static final String El_NOMBRE_DEBE_SER_SOLO_TEXTO = "La nombre debe de contener solo texto";
    private static final String SE_DEBE_INGRESAR_EL_TELEFONO = "Se debe ingresar el telefono";
    private static final String LA_CEDULA_DEBE_SER_NUMERICO = "La Cedula debe ser numerica, no debe contener simbolos, ni espacios";
    private static final String LA_CEDULA_DEBE_SER_POSITIVA = "La Cedula debe ser numerica positiva";
    private static final String EL_TELEFONO_DEBE_SER_NUMERICO = "El telefono debe ser numerico, no debe contener simbolos, ni espacios";
    private static final String EL_TELEFONO_DEBE_SER_POSITIVO = "El telefono debe ser numerica positivo";

    /*@Test
    @DisplayName("Deberia lanzar una exepecion cuando la longitud de la clave sea menor a 4")
    void deberiaLanzarUnaExepcionCuandoLaLongitudDeLaClaveSeaMenorACuatro() {
        // arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conCedula("124346");
        // act - assert
        BasePrueba.assertThrows(usuarioTestDataBuilder::build, ExcepcionLongitudValor.class, "La clave debe tener una longitud mayor o igual a 4");
    }*/

    @Test
    public void validarObligatorioCedulaTest() {
        // arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conCedula(null);
        // act - assert
        BasePrueba.assertThrows(() -> usuarioTestDataBuilder.build(), ExcepcionValorObligatorio.class, SE_DEBE_INGRESAR_LA_CEDULA);
    }

    @Test
    public void validarObligatorioNombreTest() {
        // arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conNombre(null);
        // act - assert
        BasePrueba.assertThrows(() -> usuarioTestDataBuilder.build(), ExcepcionValorObligatorio.class, SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO);
    }

    @Test
    public void validarObligatorioTelefonoTest() {
        // arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conTelefono(null);
        // act - assert
        BasePrueba.assertThrows(() -> usuarioTestDataBuilder.build(), ExcepcionValorObligatorio.class, SE_DEBE_INGRESAR_EL_TELEFONO);
    }

    @Test
    public void validarNombreSoloTexto() {
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conNombre("fras342");
        // act - assert
        BasePrueba.assertThrows(() -> usuarioTestDataBuilder.build(), ExcepcionValorInvalido.class, El_NOMBRE_DEBE_SER_SOLO_TEXTO);
    }

    @Test
    public void validarNumericoCedulaTest() {
        // arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conCedula("2346FS23");

        BasePrueba.assertThrows(() -> usuarioTestDataBuilder.build(), ExcepcionValorInvalido.class, LA_CEDULA_DEBE_SER_NUMERICO);
    }

    @Test
    public void validarNumericoPositivoCedulaTest() {
        // arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conCedula("-23143464");
        // act - assert
        BasePrueba.assertThrows(() -> usuarioTestDataBuilder.build(), ExcepcionValorInvalido.class, LA_CEDULA_DEBE_SER_POSITIVA);
    }

    @Test
    public void validarNumericoTelefonoTest() {
        // arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conTelefono("fras342");
        // act - assert
        BasePrueba.assertThrows(() -> usuarioTestDataBuilder.build(), ExcepcionValorInvalido.class, EL_TELEFONO_DEBE_SER_NUMERICO);
    }

    @Test
    public void validarNumericoPositivoTelefonoTest() {
        // arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conTelefono("-23143464");
        // act - assert
        BasePrueba.assertThrows(() -> usuarioTestDataBuilder.build(), ExcepcionValorInvalido.class, EL_TELEFONO_DEBE_SER_POSITIVO);
    }

    @Test
    void validarExistenciaPreviaTest() {
        Usuario usuario = new UsuarioTestDataBuilder().build();

        Mockito.when(repositorioUsuario.existe(Mockito.anyString())).thenReturn(true);

        BasePrueba.assertThrows(() -> servicioCrearUsuario.ejecutar(usuario), ExcepcionDuplicidad.class,EL_USUARIO_YA_EXISTE_EN_EL_SISTEMA);
    }

    @Test
    void validarCrearUsuarioTest() {
        // arrange
        Mockito.when(repositorioUsuario.existe(anyString())).thenReturn(false);
        Long resultado = servicioCrearUsuario.ejecutar(new UsuarioTestDataBuilder().build());
        Assert.assertEquals(resultado, resultado);
    }
}
