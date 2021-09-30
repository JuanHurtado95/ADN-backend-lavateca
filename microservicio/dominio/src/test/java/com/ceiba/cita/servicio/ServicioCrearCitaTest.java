package com.ceiba.cita.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.testdatabuilder.CitaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ServicioCrearCitaTest {

    private static final String SE_DEBE_INGRESA_LA_CEDULA_DEL_USUARIO = "Se debe ingresar la cedula del usuario";
    private static final String SE_DEBE_INGRESAR_LA_FECHA = "Se debe ingresar la fecha";
    private static final String SE_DEBE_INGRESAR_EL_TIPO_DE_VEHICULO = "Se debe ingresar el tipo de vehiculo";
    private static final String SE_DEBE_INGRESAR_LA_PLACA_DEL_VEHICULO = "Se debe ingresar la placa del vehiculo";
    private static final String LA_CEDULA_DEBE_SER_NUMERICA = "La Cedula debe ser numerica, no debe contener simbolos, ni espacios";
    private static final String LA_CEDULA_DEBE_SER_POSITIVA = "La Cedula debe ser numerica positiva";
    private static final String TIPO_DE_PLACA_ERRONEA = "la placa ingresada es incorrecta, debe ser alfanumerica";
    private static final String LA_CITA_YA_EXISTE_EN_EL_SISTEMA = "La cita para el vehiculo ya existe en el sistema";
    private static final String EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El usuario no esta registrado en el sistema";

    @Mock
    private RepositorioCita repositorioCita;

    @Mock
    private RepositorioUsuario repositorioUsuario;

    @InjectMocks
    private ServicioCrearCita servicioCrearCita;

    public ServicioCrearCitaTest() {
         MockitoAnnotations.openMocks(this);
    }

    @Test
    public void validarCedulaObligatoriaTest() {
        // arrange
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conCedula(null);

        BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorObligatorio.class, SE_DEBE_INGRESA_LA_CEDULA_DEL_USUARIO);
    }

    @Test
    public void validarFechaObligatoriaTest() {
        // arrange
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFecha(null);

        BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorObligatorio.class, SE_DEBE_INGRESAR_LA_FECHA);
    }

    @Test
    public void validarTipoVehiculoObligatorioTest() {
        // arrange
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conTipoVehiculo(null);

        BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorObligatorio.class, SE_DEBE_INGRESAR_EL_TIPO_DE_VEHICULO);
    }

    @Test
    public void validarPlacaObligatoriaTest() {
        // arrange
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conPlaca(null);

        BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorObligatorio.class, SE_DEBE_INGRESAR_LA_PLACA_DEL_VEHICULO);
    }

    @Test
    public void validarNumericoCedula() {
        // arrange
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conCedula("2346FS23");

        BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, LA_CEDULA_DEBE_SER_NUMERICA);
    }

    @Test
    public void validarCedulaPositiva() {
        // arrange
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conCedula("-23463423");

        BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, LA_CEDULA_DEBE_SER_POSITIVA);
    }

    @Test
    public void validarPlacaAlfanumerica() {
        // arrange
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conPlaca("23134-6");

        BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, TIPO_DE_PLACA_ERRONEA);
    }

    @Test
    public void validarExistenciaPorPlacaFecha() {
        // arrange
        Cita cita = new CitaTestDataBuilder().build();
        Mockito.when(repositorioCita.existe(cita.getPlaca(), cita.getFecha())).thenReturn(true);

        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionValorInvalido.class, LA_CITA_YA_EXISTE_EN_EL_SISTEMA);
    }

    @Test
    public void validarExistenciaUsuarioTest(){
        Cita cita = new CitaTestDataBuilder().build();
        Mockito.when(repositorioCita.existePersona(cita.getCedulaUsuario())).thenReturn(false);

        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionValorInvalido.class, EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA);
    }
}
