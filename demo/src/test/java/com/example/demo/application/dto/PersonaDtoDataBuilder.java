package com.example.demo.application.dto;

public class PersonaDtoDataBuilder {

    private Integer idPersona;

    private String nombre;

    private String identificacion;

    private String genero;

    private Integer edad;

    private String direccion;

    private String telefono;

    public PersonaDtoDataBuilder withIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
        return this;
    }

    public PersonaDtoDataBuilder withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public PersonaDtoDataBuilder withIdentificacion(String identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public PersonaDtoDataBuilder withGenero(String genero) {
        this.genero = genero;
        return this;
    }

    public PersonaDtoDataBuilder withEdad(Integer edad) {
        this.edad = edad;
        return this;
    }

    public PersonaDtoDataBuilder withDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public PersonaDtoDataBuilder withTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public PersonaDto build() {
        return new PersonaDto(idPersona, nombre, identificacion, genero, edad, direccion, telefono);
    }
}
