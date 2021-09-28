package com.ceiba.cita.comando.fabrica;

import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.cita.modelo.entidad.Cita;
import org.springframework.stereotype.Component;

@Component
public class FabricaCita {

    public Cita crear(ComandoCita comandoCita){
        return new Cita(
                comandoCita.getId(),
                comandoCita.getCedulaUsuario(),
                comandoCita.getFecha(),
                comandoCita.getTipoVehiculo(),
                comandoCita.getPlaca(),
                comandoCita.getValor()

        );
    }
}
