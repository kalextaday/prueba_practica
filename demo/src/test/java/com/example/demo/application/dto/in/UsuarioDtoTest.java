package com.example.demo.application.dto.in;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class UsuarioDtoTest {

    @Test
    public void testCrearUsuarioExitoso(){
        UsuarioDto usuarioDto = new UsuarioDtoDataBuilder()
                .withNombre("Juan")
                .withIdentificacion("123456789")
                .withGenero("M")
                .withEdad(20)
                .withDireccion("Calle falsa 123")
                .withTelefono("123456789")
                .withContrasena("123456")
                .withEstadoCliente(true)
                .build();

        Assertions.assertEquals("Juan", usuarioDto.getNombre());
        Assertions.assertEquals("123456789", usuarioDto.getIdentificacion());
        Assertions.assertEquals("M", usuarioDto.getGenero());
        Assertions.assertEquals(20, usuarioDto.getEdad());
        Assertions.assertEquals("Calle falsa 123", usuarioDto.getDireccion());
        Assertions.assertEquals("123456789", usuarioDto.getTelefono());
        Assertions.assertEquals("123456", usuarioDto.getContrasena());
        Assertions.assertEquals(true, usuarioDto.getEstadoCliente());

    }
}