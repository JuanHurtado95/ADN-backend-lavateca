package com.ceiba.tipo_vehiculo.controlador;

import com.ceiba.tipo_vehiculo.consulta.ManejadorListarTipoVehiculos;
import com.ceiba.tipo_vehiculo.modelo.dto.DtoTipoVehiculo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipoVehiculos")
@Api(tags={"Controlador consulta tipo vehiculos"})
public class ConsultaControladorTipoVehiculo {

    private ManejadorListarTipoVehiculos manejadorListarTipoVehiculos;

    public ConsultaControladorTipoVehiculo(ManejadorListarTipoVehiculos manejadorListarTipoVehiculos) {
        this.manejadorListarTipoVehiculos = manejadorListarTipoVehiculos;
    }

    @GetMapping
    @ApiOperation("Listar tipo vehiculos")
    public List<DtoTipoVehiculo> listar() {
        return this.manejadorListarTipoVehiculos.ejecutar();
    }
}
