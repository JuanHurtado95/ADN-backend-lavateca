package com.ceiba.cita.adaptador.repositorio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class RepositorioCitaMysql implements RepositorioCita {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "cita", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "cita", value = "actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace = "cita", value = "eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace = "cita", value = "existe")
    private static String sqlExiste;

    @SqlStatement(namespace = "cita", value = "existePersona")
    private static String sqlExistePersona;

    @SqlStatement(namespace = "cita", value = "fechaCita")
    private static String sqlFechaCita;

    @SqlStatement(namespace = "cita", value = "existeCitaId")
    private static String sqlExisteCitaId;

    public RepositorioCitaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Cita cita) {
        return this.customNamedParameterJdbcTemplate.crear(cita, sqlCrear);
    }

    @Override
    public void actualizar(Cita cita) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", cita.getId());
        paramSource.addValue("fecha", cita.getFecha());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizar, paramSource);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    @Override
    public boolean existe(String placa, String fecha) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("placa", placa);
        paramSource.addValue("fecha", fecha);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste, paramSource, Boolean.class);
    }

    @Override
    public boolean existePersona(String cedula) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("cedula", cedula);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePersona, paramSource, Boolean.class);
    }

    @Override
    public LocalDate fechaCita(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlFechaCita, paramSource, LocalDate.class);
    }

    @Override
    public boolean existe(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteCitaId, paramSource, Boolean.class);
    }

}
