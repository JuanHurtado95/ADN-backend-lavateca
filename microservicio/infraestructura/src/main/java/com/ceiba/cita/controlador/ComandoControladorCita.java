package com.ceiba.cita.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.cita.comando.manejador.ManejadorActualizarCita;
import com.ceiba.cita.comando.manejador.ManejadorCrearCita;
import com.ceiba.cita.comando.manejador.ManejadorEliminarCita;
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
    private final ManejadorEliminarCita manejadorEliminarCita;

    @Autowired
    public ComandoControladorCita(ManejadorCrearCita manejadorCrearCita, ManejadorActualizarCita manejadorActualizarCita, ManejadorEliminarCita manejadorEliminarCita) {
        this.manejadorCrearCita = manejadorCrearCita;
        this.manejadorActualizarCita = manejadorActualizarCita;
        this.manejadorEliminarCita = manejadorEliminarCita;
    }

    @PostMapping
    @ApiOperation("Crear Cita")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoCita comandoCita) {
        return manejadorCrearCita.ejecutar(comandoCita);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Reprogramar Cita")
    public void actualizar(@RequestBody ComandoCita comandoCita, @PathVariable Long id){
        comandoCita.setId(id);
        manejadorActualizarCita.ejecutar(comandoCita);
    }

    @DeleteMapping(value="/{id}")
    @ApiOperation("Eliminar Usuario")
    public void eliminar(@PathVariable Long id) {
        manejadorEliminarCita.ejecutar(id);
    }

}
