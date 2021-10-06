package com.ceiba.cita.testdatabuilder;

import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.usuario.comando.ComandoUsuario;

public class ComandoCitaTestDataBuilder {

    private Long id;
    private Long idVehiculo;
    private String fecha;

    public ComandoCitaTestDataBuilder(){
        id=2l;
        idVehiculo = 1l;
        fecha="2021-10-06";
    }

    public ComandoCitaTestDataBuilder conIdVehiculo(Long idVehiculo){
        this.idVehiculo = idVehiculo;
        return this;
    }

    public  ComandoCitaTestDataBuilder conFecha(String fecha){
        this.fecha = fecha;
        return this;
    }

    public ComandoCita build() {
        return new ComandoCita(id, idVehiculo, fecha);
    }

}
