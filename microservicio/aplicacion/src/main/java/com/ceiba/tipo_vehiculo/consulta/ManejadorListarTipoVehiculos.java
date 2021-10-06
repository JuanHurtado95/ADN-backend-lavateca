package com.ceiba.tipo_vehiculo.consulta;

import com.ceiba.tipo_vehiculo.modelo.dto.DtoTipoVehiculo;
import com.ceiba.tipo_vehiculo.puerto.dao.DaoTipoVehiculo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarTipoVehiculos {

    private final DaoTipoVehiculo daoTipoVehiculo;

    public ManejadorListarTipoVehiculos(DaoTipoVehiculo daoTipoVehiculo){

        this.daoTipoVehiculo = daoTipoVehiculo;
    }

    public List<DtoTipoVehiculo> ejecutar(){
        return this.daoTipoVehiculo.listar();
    }
}
