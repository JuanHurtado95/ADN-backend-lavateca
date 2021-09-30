package com.ceiba.cita.servicio.testdatabuilder;

import com.ceiba.cita.modelo.entidad.Cita;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

public class CitaTestDataBuilder {

    private Long id;
    private String cedulaUsuario;
    private String fecha;
    private String tipoVehiculo;
    private String placa;
    private Double valor;

    public CitaTestDataBuilder() {
        this.id = 1l;
        this.cedulaUsuario = "24556756";
        this.fecha = "2021-09-15";
        this.tipoVehiculo = "moto";
        this.placa = "GFD231";
        this.valor = 10.000;

    }

    public CitaTestDataBuilder conCedula(String cedula){
        this.cedulaUsuario = cedula;
        return this;
    }

    public CitaTestDataBuilder conFecha(String fecha){
        this.fecha = fecha;
        return this;
    }

    public CitaTestDataBuilder conTipoVehiculo(String tipoVehiculo){
        this.tipoVehiculo = tipoVehiculo;
        return this;
    }

    public CitaTestDataBuilder conPlaca(String placa){
        this.placa = placa;
        return this;
    }

    public CitaTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public Cita build() {

        return new Cita(cedulaUsuario, fecha, tipoVehiculo, placa);
    }
}
