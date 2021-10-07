package com.ceiba.cita.modelo.entidad;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
@Setter
@AllArgsConstructor
public class Cita {

    private static final String SE_DEBE_INGRESAR_UN_ID_DE_VEHICULO = "Se debe ingresar un identificador de un vehiculo";
    private static final String SE_DEBE_INGRESAR_LA_FECHA = "Se debe ingresar la fecha";

    private static final String FORMATO_FECHA = "yyyy-MM-dd";
    private static final String FORMATO_DE_FECHA_DE_COMPRA_ERRONEA = "Formato de fecha de compra es erronea, debe tener la estructura YYYYMMDD";
    private static final String LA_FECHA_DE_CITA_INVALIDA = "La fecha de cita es invalida";
    private static final String NO_SE_PUEDE_MODIFICAR_LA_CITA = "la cita no se puede modificar, un dia antes de la fecha programada";
    private static final String NO_SE_AGENDAN_CITAS_SABADOS_Y_DOMINGOS = "No se agenda citas los dias sabados y domingos";

    private Long id;
    private Long idVehiculo;
    private LocalDate fecha;

    public Cita(Long id, Long idVehiculo, String fecha) {

        validarObligatorio(idVehiculo, SE_DEBE_INGRESAR_UN_ID_DE_VEHICULO);
        validarObligatorio(fecha, SE_DEBE_INGRESAR_LA_FECHA);

        validarFormatoFecha(fecha, FORMATO_DE_FECHA_DE_COMPRA_ERRONEA);
        validarFinDeSemana(convertirStringADate(fecha), NO_SE_AGENDAN_CITAS_SABADOS_Y_DOMINGOS);

        LocalDate fechaCita = convertirStringADate(fecha);

        this.id = id;
        this.idVehiculo = idVehiculo;
        this.fecha = fechaCita;
    }

    private static void validarFormatoFecha(String fecha, String mensaje) {

        Pattern formatoFecha = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher servicioFormatoFecha = formatoFecha.matcher(fecha);
        if (!servicioFormatoFecha.matches()) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    private void validarFinDeSemana(LocalDate fechaServicio, String mensaje) {
        if (fechaServicio.getDayOfWeek() == DayOfWeek.SATURDAY || fechaServicio.getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    private LocalDate convertirStringADate(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        return LocalDate.parse(fecha, formatter);
    }
}
