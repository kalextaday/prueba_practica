package com.example.demo.application.dto;

public class ClienteDtoDataBuilder {

    private Integer idCliente;

    private Integer idPersona;

    private String contrasena;

    private Boolean estadoCliente;

    public ClienteDtoDataBuilder withIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public ClienteDtoDataBuilder withIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
        return this;
    }

    public ClienteDtoDataBuilder withContrasena(String contrasena) {
        this.contrasena = contrasena;
        return this;
    }

    public ClienteDtoDataBuilder withEstadoCliente(Boolean estadoCliente) {
        this.estadoCliente = estadoCliente;
        return this;
    }

    public ClienteDto build() {
        return new ClienteDto(idCliente, idPersona, contrasena, estadoCliente);
    }
}
