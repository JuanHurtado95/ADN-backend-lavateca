package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CitaTest {

    @Mock
    private RepositorioCita repositorioCita;

    @InjectMocks
    private ServicioCrearCita servicioCrearCita;

    private Cita cita;

    /*@Test
    void validarCedulaObligatoriaTest(){

        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conCedula(null);

        BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorObligatorio.class, "Se debe ingresar la cedula del usuario");
    }*/

}
