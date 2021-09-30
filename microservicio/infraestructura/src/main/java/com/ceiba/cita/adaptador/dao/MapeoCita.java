package com.ceiba.cita.adaptador.dao;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoCita implements RowMapper<DtoCita>, MapperResult {

    @Override
    public DtoCita mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("id");
        String cedulaUsuario = resultSet.getString("cedula_usuario");
        String fecha = resultSet.getString("fecha");
        String tipoVehiculo = resultSet.getString("tipo_vehiculo");
        String placa = resultSet.getString("placa");
        Double valor = resultSet.getDouble("valor");

        return new DtoCita(id, cedulaUsuario, fecha, tipoVehiculo, placa, valor);

    }

}
