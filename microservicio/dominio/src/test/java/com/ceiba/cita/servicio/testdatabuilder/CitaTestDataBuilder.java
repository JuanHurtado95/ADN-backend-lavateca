package com.ceiba.cita.servicio.testdatabuilder;

import com.ceiba.cita.modelo.entidad.Cita;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CitaTestDataBuilder {

    private Long id;
    private Long idVehiculo;
    private String fecha;

    public CitaTestDataBuilder() {
        this.id = 1l;
        this.idVehiculo = 1l;
        this.fecha = "2021-09-15";

    }

    public CitaTestDataBuilder conIdVehiculo(Long idVehiculo){
        this.idVehiculo = idVehiculo;
        return this;
    }

    public CitaTestDataBuilder conFecha(String fecha){
        this.fecha = fecha;
        return this;
    }

    public CitaTestDataBuilder conFechaActual(){
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = fechaActual.format(formatter);
        this.fecha = formattedString;
        return this;
    }

    public CitaTestDataBuilder conFechaVencida(){
        LocalDate fechaActual = LocalDate.now().plusDays(-1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = fechaActual.format(formatter);
        this.fecha = formattedString;
        return this;
    }

    public CitaTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public Cita build() {

        return new Cita(id, idVehiculo, fecha);
    }
}
