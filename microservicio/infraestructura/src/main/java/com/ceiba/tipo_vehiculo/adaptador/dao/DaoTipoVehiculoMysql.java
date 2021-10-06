package com.ceiba.tipo_vehiculo.adaptador.dao;

import com.ceiba.cita.adaptador.dao.MapeoCita;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.tipo_vehiculo.modelo.dto.DtoTipoVehiculo;
import com.ceiba.tipo_vehiculo.puerto.dao.DaoTipoVehiculo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoTipoVehiculoMysql implements DaoTipoVehiculo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "tipo_vehiculo", value = "listar")
    private static String sqlListar;

    public DaoTipoVehiculoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoTipoVehiculo> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoTipoVehiculo());
    }
}
