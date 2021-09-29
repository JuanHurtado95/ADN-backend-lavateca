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
import static com.ceiba.dominio.ValidadorArgumento.validarPositivo;

@Getter
@Setter
@AllArgsConstructor
public class Cita {

    private static final String SE_DEBE_INGRESA_LA_CEDULA_DEL_USUARIO = "Se debe ingresar la cedula del usuario";
    private static final String SE_DEBE_INGRESAR_LA_FECHA = "Se debe ingresar la fecha";
    private static final String SE_DEBE_INGRESAR_EL_TIPO_DE_VEHICULO = "Se debe ingresar el tipo de vehiculo";
    private static final String SE_DEBE_INGRESAR_LA_PLACA_DEL_VEHICULO = "Se debe ingresar la placa del vehiculo";
    private static final String SE_DEBE_INGRESAR_EL_VALOR = "Se debe ingresar el valor";
    private static final String LA_CEDULA_DEBE_SER_NUMERICA = "La Cedula debe ser numerica, no debe contener simbolos, ni espacios";
    private static final String LA_CEDULA_DEBE_SER_POSITIVA = "La Cedula debe ser numerica positiva";

    private static final String FORMATO_FECHA = "yyyy-MM-dd";
    private static final String FORMATO_DE_FECHA_DE_COMPRA_ERRONEA = "Formato de fecha de compra es erronea";
    private static final String LA_FECHA_DE_CITA_INVALIDA = "La fecha de cita es invalida";
    private static final String NO_SE_PUEDE_MODIFICAR_LA_CITA = "la cita no se puede modificar, un dia antes de la fecha programada";
    private static final String NO_SE_AGENDAN_CITAS_SABADOS_Y_DOMINGOS = "No se agenda citas los dias sabados y domingos";

    private static final String TIPO_DE_VEHICULO_EQUIVOCADO = "Este tipo de vehiculo no existe en nuestro sistema";
    private static final String TIPO_DE_VEHICULO_CARRO = "CARRO";
    private static final String TIPO_DE_VEHICULO_MOTO = "MOTO";
    private static final String TIPO_DE_PLACA_ERRONEA = "la placa ingresada es incorrecta";
    private static final int NUMERO_CARACTERES_PLACA = 6;
    private static final double VALOR_SERVICIO_CARRO = 20000;
    private static final double VALOR_SERVICIO_MOTO = 10000;
    private static final String VALOR_SERVICIO_EQUIVOCADO = "Valor del servicio equivocadp";


    private Long id;
    private String cedulaUsuario;
    private String fecha;
    private String tipoVehiculo;
    private String placa;
    private Double valor;

    public Cita(Long id, String fecha) {

        this.id = id;
        this.fecha = fecha;

    }
    public Cita(String cedulaUsuario, String fecha, String tipoVehiculo, String placa) {

        validarObligatorio(cedulaUsuario, SE_DEBE_INGRESA_LA_CEDULA_DEL_USUARIO);
        validarObligatorio(fecha, SE_DEBE_INGRESAR_LA_FECHA);
        validarObligatorio(tipoVehiculo, SE_DEBE_INGRESAR_EL_TIPO_DE_VEHICULO);
        validarObligatorio(placa, SE_DEBE_INGRESAR_LA_PLACA_DEL_VEHICULO);

        validarAlfanumerico(placa, TIPO_DE_PLACA_ERRONEA);
        validarPlaca(placa, TIPO_DE_PLACA_ERRONEA);

        validarFormatoFecha(formatoFecha(fecha), FORMATO_DE_FECHA_DE_COMPRA_ERRONEA);
        validarFinDeSemana(convertirStringADate(fecha), NO_SE_AGENDAN_CITAS_SABADOS_Y_DOMINGOS);
        validarNumerico(cedulaUsuario, LA_CEDULA_DEBE_SER_NUMERICA);
        validarPositivo(Double.parseDouble(cedulaUsuario), LA_CEDULA_DEBE_SER_POSITIVA);
        validarTipoVehiculo(tipoVehiculo, TIPO_DE_VEHICULO_EQUIVOCADO);
        registrarValorServicio(tipoVehiculo);

        this.cedulaUsuario = cedulaUsuario;
        this.fecha = fecha;
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo.toUpperCase();
    }

    private static void validarFormatoFecha(String fecha, String mensaje) {

        Pattern formatoFecha = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher servicioFormatoFecha = formatoFecha.matcher(fecha);
        if (!servicioFormatoFecha.matches()) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    private String formatoFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        return formatter.format(LocalDate.parse(fecha, formatter));
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

    private void validarTipoVehiculo(String tipoVehiculo, String mensaje){
        if(!TIPO_DE_VEHICULO_CARRO.equals(tipoVehiculo.toUpperCase())  && !TIPO_DE_VEHICULO_MOTO.equals(tipoVehiculo.toUpperCase())){
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    private void registrarValorServicio(String tipoVehiculo){
        if(TIPO_DE_VEHICULO_CARRO.equals(tipoVehiculo.toUpperCase())){
            setValor(VALOR_SERVICIO_CARRO);
        }else{
            setValor(VALOR_SERVICIO_MOTO);
        }
    }

    private void validarPlaca(String valor, String mensaje){
        if(NUMERO_CARACTERES_PLACA != valor.length()){
            throw new ExcepcionValorInvalido(mensaje);
        }
    }
}
