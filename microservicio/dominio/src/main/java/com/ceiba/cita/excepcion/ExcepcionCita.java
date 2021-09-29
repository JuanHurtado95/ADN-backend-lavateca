package com.ceiba.cita.excepcion;

public class ExcepcionCita extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ExcepcionCita(String message) {
        super(message);
    }
}
