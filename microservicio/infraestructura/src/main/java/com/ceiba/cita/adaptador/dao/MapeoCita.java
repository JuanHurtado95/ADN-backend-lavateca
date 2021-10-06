package com.ceiba.cita.adaptador.dao;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.tipo_vehiculo.modelo.dto.DtoTipoVehiculo;
import com.ceiba.tipo_vehiculo.puerto.dao.DaoTipoVehiculo;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MapeoCita implements RowMapper<DtoCita>, MapperResult {

    @Override
    public DtoCita mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("id");
        LocalDate fecha = extraerLocalDate(resultSet, "fecha");

        Long idUsuario = resultSet.getLong("id_usuario");
        String cedula = resultSet.getString("cedula");
        String nombre = resultSet.getString("nombre");
        String telefono = resultSet.getString("telefono");

        Long idTipoVehiculo = resultSet.getLong("id_tipo_vehiculo");
        String nombreTipoVehiculo = resultSet.getString("nombre_tipo_vehiculo");
        double precioLavada = resultSet.getDouble("precio_lavada");

        Long idVehiculo = resultSet.getLong("id_vehiculo");
        String placa = resultSet.getString("placa");

        DtoTipoVehiculo tipoVehiculo = new DtoTipoVehiculo(idTipoVehiculo, nombreTipoVehiculo, precioLavada);
        DtoUsuario usuario = new DtoUsuario(idUsuario, cedula, nombre, telefono);
        DtoVehiculo vehiculo = new DtoVehiculo(idVehiculo, usuario, tipoVehiculo, placa);

        return new DtoCita(id, vehiculo, fecha);

    }

}
