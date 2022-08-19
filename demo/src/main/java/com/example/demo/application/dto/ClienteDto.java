package com.example.demo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private Integer idCliente;

    private Integer idPersona;

    private String contrasena;

    private Boolean estadoCliente;

    public ClienteDto(String contrasena, Boolean estadoCliente) {
        this.contrasena = contrasena;
        this.estadoCliente = estadoCliente;
    }
}
