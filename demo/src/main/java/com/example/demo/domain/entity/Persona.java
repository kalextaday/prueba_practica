package com.example.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "persona")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersona;

    private String nombre;

    private String identificacion;

    private String genero;

    private Integer edad;

    private String direccion;

    private String telefono;

}
