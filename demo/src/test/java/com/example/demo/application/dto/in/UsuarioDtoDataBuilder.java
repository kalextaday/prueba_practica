package com.example.demo.application.dto.in;

public class UsuarioDtoDataBuilder {

    private String nombre;

    private String identificacion;

    private String genero;

    private Integer edad;

    private String direccion;

    private String telefono;

    private String contrasena;

    private Boolean estadoCliente;

    public UsuarioDtoDataBuilder withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public UsuarioDtoDataBuilder withIdentificacion(String identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public UsuarioDtoDataBuilder withGenero(String genero) {
        this.genero = genero;
        return this;
    }

    public UsuarioDtoDataBuilder withEdad(Integer edad) {
        this.edad = edad;
        return this;
    }

    public UsuarioDtoDataBuilder withDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public UsuarioDtoDataBuilder withTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public UsuarioDtoDataBuilder withContrasena(String contrasena) {
        this.contrasena = contrasena;
        return this;
    }

    public UsuarioDtoDataBuilder withEstadoCliente(Boolean estadoCliente) {
        this.estadoCliente = estadoCliente;
        return this;
    }


    public UsuarioDto build() {
        return new UsuarioDto(nombre, identificacion, genero, edad, direccion, telefono, contrasena, estadoCliente);
    }
}
