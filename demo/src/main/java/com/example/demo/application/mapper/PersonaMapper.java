package com.example.demo.application.mapper;

import com.example.demo.application.dto.PersonaDto;
import com.example.demo.domain.entity.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public abstract class PersonaMapper {

    public static final PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);


    @Mapping(source = "persona.idPersona",                    target = "idPersona")
    @Mapping(source = "persona.nombre",                    target = "nombre")
    @Mapping(source = "persona.identificacion",                    target = "identificacion")
    @Mapping(source = "persona.genero",                    target = "genero")
    @Mapping(source = "persona.edad",                    target = "edad")
    @Mapping(source = "persona.direccion",                    target = "direccion")
    @Mapping(source = "persona.telefono",                    target = "telefono")
    public abstract PersonaDto personaToPersonaDto(Persona persona);


    @Mapping(source = "personaDto.nombre",                    target = "nombre")
    @Mapping(source = "personaDto.identificacion",                    target = "identificacion")
    @Mapping(source = "personaDto.genero",                    target = "genero")
    @Mapping(source = "personaDto.edad",                    target = "edad")
    @Mapping(source = "personaDto.direccion",                    target = "direccion")
    @Mapping(source = "personaDto.telefono",                    target = "telefono")
    @Mapping(source = "personaDto.idPersona",                    target = "idPersona")
    public abstract Persona personaDtoToPersona(PersonaDto personaDto);

    public List<PersonaDto> personaListToPersonaDtoList(List<Persona> data) {
        return data.stream()
                .map(this::personaToPersonaDto)
                .collect(Collectors.toList());
    }
}
