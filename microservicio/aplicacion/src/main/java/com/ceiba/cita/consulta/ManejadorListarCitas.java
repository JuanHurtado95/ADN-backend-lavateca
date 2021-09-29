package com.ceiba.cita.consulta;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.puerto.dao.DaoCita;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public class ManejadorListarCitas {

    private final DaoCita daoCita;

    public ManejadorListarCitas(DaoCita daoCita) {
        this.daoCita = daoCita;
    }

    public List<DtoCita> ejecutar(){
        List<DtoCita> listar = this.daoCita.listar();
        return listar;
    }

}
