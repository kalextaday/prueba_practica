package com.example.demo.application.dto;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class ClienteDtoTest {

    @Test
    public void testCrearClienteExitoso(){
        ClienteDto clienteDto = new ClienteDtoDataBuilder()
                .withContrasena("123456")
                .withEstadoCliente(true)
                .build();

        Assertions.assertEquals("123456", clienteDto.getContrasena());
        Assertions.assertEquals(true, clienteDto.getEstadoCliente());
    }

}