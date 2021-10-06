package com.ceiba.vehiculo.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.tipo_vehiculo.modelo.dto.DtoTipoVehiculo;
import com.ceiba.tipo_vehiculo.puerto.dao.DaoTipoVehiculo;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoVehiculo implements RowMapper<DtoVehiculo>, MapperResult {


    @Override
    public DtoVehiculo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("id");
        String placa = resultSet.getString("placa");

        Long idUsuario = resultSet.getLong("id_usuario");
        String cedula = resultSet.getString("cedula");
        String nombreUsuario = resultSet.getString("nombre");
        String telefono = resultSet.getString("telefono");

        Long idTipoVehiculo = resultSet.getLong("id_tipo_vehiculo");
        String nombreVehiculo = resultSet.getString("nombre_vehiculo");
        double precioLavada = resultSet.getDouble("precio_lavada");

        DtoUsuario usuario = new DtoUsuario(idUsuario, cedula, nombreUsuario, telefono);
        DtoTipoVehiculo tipoVehiculo = new DtoTipoVehiculo(idTipoVehiculo, nombreVehiculo, precioLavada);

        return new DtoVehiculo(id, usuario, tipoVehiculo, placa);
    }
}
