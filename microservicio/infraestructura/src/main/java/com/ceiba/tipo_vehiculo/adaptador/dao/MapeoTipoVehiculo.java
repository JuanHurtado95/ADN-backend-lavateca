package com.ceiba.tipo_vehiculo.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.tipo_vehiculo.modelo.dto.DtoTipoVehiculo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoTipoVehiculo implements RowMapper<DtoTipoVehiculo>, MapperResult {

    @Override
    public DtoTipoVehiculo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        Double precioLavada = resultSet.getDouble("precio_lavada");

        return new DtoTipoVehiculo(id, nombre, precioLavada);
    }
}
