package com.example.demo.application.dto;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class PersonaDtoTest {

    @Test
    public void testCrearPersonaExitoso(){
        PersonaDto personaDto = new PersonaDtoDataBuilder()
                .withNombre("Juan")
                .withIdentificacion("123456789")
                .withGenero("M")
                .withEdad(20)
                .withDireccion("Calle falsa 123")
                .withTelefono("123456789")
                .build();

        Assertions.assertEquals("Juan", personaDto.getNombre());
        Assertions.assertEquals("123456789", personaDto.getIdentificacion());
        Assertions.assertEquals("M", personaDto.getGenero());
        Assertions.assertEquals(20, personaDto.getEdad());
        Assertions.assertEquals("Calle falsa 123", personaDto.getDireccion());
        Assertions.assertEquals("123456789", personaDto.getTelefono());
    }

}