package com.ceiba.cita.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.testdatabuilder.CitaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import org.junit.Assert;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class ServicioCrearCitaTest {

    private static final String SE_DEBE_INGRESAR_UN_ID_DE_VEHICULO = "Se debe ingresar un identificador de un vehiculo";
    private static final String SE_DEBE_INGRESAR_LA_FECHA = "Se debe ingresar la fecha";
    private static final String LA_CITA_YA_EXISTE_EN_EL_SISTEMA = "La cita para el vehiculo ya existe en el sistema";
    private static final String EL_VEHICULO_NO_EXISTE_EN_EL_SISTEMA = "El vehiculo no esta registrado en el sistema";
    private static final String NO_SE_AGENDAN_CITAS_SABADOS_Y_DOMINGOS = "No se agenda citas los dias sabados y domingos";
    private static final String FORMATO_DE_FECHA_DE_COMPRA_ERRONEA = "Formato de fecha de compra es erronea, debe tener la estructura YYYYMMDD";

    @Mock
    private RepositorioCita repositorioCita;

    @InjectMocks
    private ServicioCrearCita servicioCrearCita;

    public ServicioCrearCitaTest() {
         MockitoAnnotations.openMocks(this);
    }

    @Test
    public void validarIdVehiculoObligatorioTest() {
        // arrange
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conIdVehiculo(null);

        BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorObligatorio.class, SE_DEBE_INGRESAR_UN_ID_DE_VEHICULO);
    }

    @Test
    public void validarFechaObligatoriaTest() {
        // arrange
        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFecha(null);

        BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorObligatorio.class, SE_DEBE_INGRESAR_LA_FECHA);
    }

    @Test
    public void validarFormatoFechaYYYYMMDD(){

        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFecha("23-10-2021");

        BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, FORMATO_DE_FECHA_DE_COMPRA_ERRONEA);
    }

    @Test
    public void validarFechaDiferenteFinSemana(){

        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFecha("2021-10-23");

        BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, NO_SE_AGENDAN_CITAS_SABADOS_Y_DOMINGOS);
    }

    @Test
    public void validarExistenciaCitaPorIdTest() {
        // arrange
        Cita cita = new CitaTestDataBuilder().build();
        Mockito.when(repositorioCita.existe(cita.getId())).thenReturn(true);

        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionValorInvalido.class, LA_CITA_YA_EXISTE_EN_EL_SISTEMA);
    }

    @Test
    public void validarExistenciaVehiculoTest() {
        // arrange
        Cita cita = new CitaTestDataBuilder().build();
        Mockito.when(repositorioCita.existeVehiculo(cita.getIdVehiculo())).thenReturn(false);

        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionValorInvalido.class, EL_VEHICULO_NO_EXISTE_EN_EL_SISTEMA);
    }

    @Test
    public void validarServicioCrearCitaTest() {
        // arrange
        Cita cita = new CitaTestDataBuilder().build();
        Mockito.when(repositorioCita.existe(cita.getId())).thenReturn(false);
        Mockito.when(repositorioCita.existeVehiculo(cita.getIdVehiculo())).thenReturn(true);
        //act
        servicioCrearCita.ejecutar(cita);
        // assert
        Mockito.verify(repositorioCita).crear(cita);
    }

}
