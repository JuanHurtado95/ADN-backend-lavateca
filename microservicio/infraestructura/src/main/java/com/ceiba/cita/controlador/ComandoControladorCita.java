package com.ceiba.cita.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.cita.comando.manejador.ManejadorActualizarCita;
import com.ceiba.cita.comando.manejador.ManejadorCrearCita;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/citas")
@Api(tags = { "Controlador comando cita"})
public class ComandoControladorCita {

    private final ManejadorCrearCita manejadorCrearCita;
    private final ManejadorActualizarCita manejadorActualizarCita;

    @Autowired
    public ComandoControladorCita(ManejadorCrearCita manejadorCrearCita, ManejadorActualizarCita manejadorActualizarCita) {
        this.manejadorCrearCita = manejadorCrearCita;
        this.manejadorActualizarCita = manejadorActualizarCita;
    }
    /*
    @PostMapping(value="/imprimir")
    @ApiOperation("Prueba")
    public @ResponseBody String imprimir(@RequestBody String id ){
        return "Hola";
    }*/

    @PostMapping
    @ApiOperation("Crear Cita")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoCita comandoCita) {
        return manejadorCrearCita.ejecutar(comandoCita);
    }

    @PutMapping(value = "{placa}")
    @ApiOperation("Actualizar Cita")
    public void actualizar(@RequestBody ComandoCita comandoCita, @PathVariable String placa){
        comandoCita.setPlaca(placa);
        manejadorActualizarCita.ejecutar(comandoCita);
    }

}