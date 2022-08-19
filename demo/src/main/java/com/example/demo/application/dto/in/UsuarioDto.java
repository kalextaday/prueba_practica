package com.example.demo.application.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private String nombre;

    private String identificacion;

    private String genero;

    private Integer edad;

    private String direccion;

    private String telefono;

    private String contrasena;

    private Boolean estadoCliente;

}
