package com.ceiba.cita.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cita.excepcion.ExcepcionCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.testdatabuilder.CitaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class ServicioActualizarCitaTest {

    private static final String LA_CITA_NO_EXISTE_EN_EL_SISTEMA = "La cita no existe en el sistema";
    private static final String NO_PUEDE_ACTUALIZAR_LA_FECHA_DE_LA_CITA = "No puede reprogramar la cita un dia antes a la fecha";
    private static final String LA_CITA_ESTA_VENCIDA = "No puede reprogramar una cita vencida";

    @Mock
    private RepositorioCita repositorioCita;

    @InjectMocks
    private ServicioActulizarCita servicioActulizarCita;

    public ServicioActualizarCitaTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void validarExistenciaCitaId() {
        // arrange
        Cita cita = new CitaTestDataBuilder().conId(5l).build();
        Mockito.when(repositorioCita.existe(Mockito.any())).thenReturn(false);

        BasePrueba.assertThrows(() -> servicioActulizarCita.ejecutar(cita), ExcepcionValorInvalido.class, LA_CITA_NO_EXISTE_EN_EL_SISTEMA);
    }

    @Test
    public void validarActualizarFechaCitaUnDiaAntesTest() {

        // arrange
        Cita cita = new CitaTestDataBuilder().conFechaActual().build();

        Mockito.when(repositorioCita.existe(Mockito.any())).thenReturn(true);
        Mockito.when(repositorioCita.fechaCita(cita.getId())).thenReturn(LocalDate.now());

        BasePrueba.assertThrows(() -> servicioActulizarCita.ejecutar(cita), ExcepcionValorInvalido.class, NO_PUEDE_ACTUALIZAR_LA_FECHA_DE_LA_CITA);
    }

    @Test
    public void validarActualizarFechaCitaFechaVencidaTest() {
        // arrange
        Cita cita = new CitaTestDataBuilder().build();

        Mockito.when(repositorioCita.existe(Mockito.any())).thenReturn(true);
        Mockito.when(repositorioCita.fechaCita(cita.getId())).thenReturn(LocalDate.now().plusDays(-2));

        BasePrueba.assertThrows(() -> servicioActulizarCita.ejecutar(cita), ExcepcionValorInvalido.class, LA_CITA_ESTA_VENCIDA);
    }

    @Test
    @DisplayName("Deberia validar actualizacion de la cita")
    void ValidarActualizacionCitaTest() {
        // arrange
        Cita cita = new CitaTestDataBuilder().conId(1L).build();
        Mockito.when(repositorioCita.existe(cita.getId())).thenReturn(true);
        Mockito.when(repositorioCita.fechaCita(cita.getId())).thenReturn(LocalDate.now().plusDays(2));
        // act
        servicioActulizarCita.ejecutar(cita);
        // assert
        Mockito.verify(repositorioCita).actualizar(cita);
    }
}
