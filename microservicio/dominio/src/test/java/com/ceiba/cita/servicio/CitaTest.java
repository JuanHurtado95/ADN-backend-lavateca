package com.ceiba.cita.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class CitaTest {

    private Cita cita;

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando no ingrese la cedula del usuario")
    void DeberiaLanzarUnaExcepcionCuandoNoIngreseLaCedulaDelUsuario(){

        BasePrueba.assertThrows(() -> new Cita(null,"2021-10-04", "moto", "DVP234"), ExcepcionValorInvalido.class, "Se debe ingresar la cedula del usuario");
    }

}
