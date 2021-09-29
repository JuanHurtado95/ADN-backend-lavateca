package com.ceiba.cita.servicio.testdatabuilder;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.usuario.modelo.entidad.Usuario;
import net.bytebuddy.implementation.bytecode.ShiftRight;

public class CitaTestDataBuilder {

    private Long id;
    private String cedulaUsuario;
    private String fecha;
    private String tipoVehiculo;
    private String placa;
    private Double valor;

    public CitaTestDataBuilder() {
        cedulaUsuario = "24556756";
        fecha = "2021-10-06";
        tipoVehiculo = "moto";
        placa = "DVP94C";
    }

    public CitaTestDataBuilder sinCedula(){
        this.cedulaUsuario = null;
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
        return new Cita(id, cedulaUsuario, fecha, tipoVehiculo, placa, valor);
    }
}
