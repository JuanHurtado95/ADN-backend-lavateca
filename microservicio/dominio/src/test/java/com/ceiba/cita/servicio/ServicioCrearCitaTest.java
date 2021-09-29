package com.ceiba.cita.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.servicio.testdatabuilder.CitaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class ServicioCrearCitaTest {

    @InjectMocks
    private ServicioCrearCita servicioCrearCita;

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando no ingrese la cedula del usuario")
    void DeberiaLanzarUnaExcepcionCuandoNoIngreseLaCedulaDelUsuario() {
        // arrange
        Cita cita = new CitaTestDataBuilder().sinCedula().build();
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionValorInvalido.class, "Se debe ingresar la cedula del usuario");
    }

}
