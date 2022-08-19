package com.example.demo.application.port.out;

import com.example.demo.application.dto.PersonaDto;

import java.util.List;

public interface IPersonaPersistenciaPort {

    PersonaDto agregarPersona(PersonaDto personaDto);

    PersonaDto editarPersona(PersonaDto personaDto);

    boolean eliminarPersona(PersonaDto personaDto);

    PersonaDto obtenerPersonaPorIdentificacion(String identificacion);

    PersonaDto obtenerPersonaPorId(Integer idPersona);

    List<PersonaDto> obtenerTodos();
}
