package com.alura.conversor;

public class ErrorConsultaAPI extends RuntimeException {

    public ErrorConsultaAPI(String mensaje) {
        super(mensaje);
    }
}