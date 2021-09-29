package com.ceiba.cita.comando.manejador;

import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.cita.comando.ComandoCitaActualizar;
import com.ceiba.cita.comando.fabrica.FabricaCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.servicio.ServicioActulizarCita;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarCita implements ManejadorComando<ComandoCitaActualizar> {

    private final FabricaCita fabricaCita;
    private final ServicioActulizarCita servicioActulizarCita;

    public ManejadorActualizarCita(FabricaCita fabricaCita, ServicioActulizarCita servicioActulizarCita) {
        this.fabricaCita = fabricaCita;
        this.servicioActulizarCita = servicioActulizarCita;
    }

    public void ejecutar(ComandoCitaActualizar comandoCita) {
        Cita cita = this.fabricaCita.crear(comandoCita);
        this.servicioActulizarCita.ejecutar(cita);
    }
}
