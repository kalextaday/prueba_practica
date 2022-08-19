package com.example.demo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDto {

    private Integer idPersona;

    private String nombre;

    private String identificacion;

    private String genero;

    private Integer edad;

    private String direccion;

    private String telefono;

    public PersonaDto(String nombre, String identificacion, String genero, Integer edad, String direccion, String telefono) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.genero = genero;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
    }
}
