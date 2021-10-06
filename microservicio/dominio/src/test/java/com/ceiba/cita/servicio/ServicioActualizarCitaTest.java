package com.ceiba.cita.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cita.excepcion.ExcepcionCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.testdatabuilder.CitaTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
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
        Cita cita = new CitaTestDataBuilder().build();
        cita.setId(5l);
        Mockito.when(repositorioCita.existe(Mockito.any())).thenReturn(false);

        BasePrueba.assertThrows(() -> servicioActulizarCita.ejecutar(cita), ExcepcionValorInvalido.class, LA_CITA_NO_EXISTE_EN_EL_SISTEMA);
    }

    @Test
    public void validarActualizarFechaCitaTest() {
        // arrange
        BasePrueba.assertThrows(() -> servicioActulizarCita.validarActualizarFechaCita(LocalDate.now().plusDays(1)), ExcepcionValorInvalido.class, NO_PUEDE_ACTUALIZAR_LA_FECHA_DE_LA_CITA);
        BasePrueba.assertThrows(() -> servicioActulizarCita.validarActualizarFechaCita(LocalDate.now().plusDays(-1)), ExcepcionValorInvalido.class, LA_CITA_ESTA_VENCIDA);
    }
}
