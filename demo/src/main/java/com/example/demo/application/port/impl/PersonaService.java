package com.example.demo.application.port.impl;

import com.example.demo.application.dto.PersonaDto;
import com.example.demo.application.mapper.PersonaMapper;
import com.example.demo.application.port.out.IPersonaPersistenciaPort;
import com.example.demo.domain.entity.Persona;
import com.example.demo.infraestructure.repository.PersonaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class PersonaService implements IPersonaPersistenciaPort {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {

        this.personaRepository = personaRepository;
    }

    @Override
    public PersonaDto agregarPersona(PersonaDto personaDto) {
        Persona personRecent = PersonaMapper.INSTANCE.personaDtoToPersona(personaDto);
        Persona personSaved = this.personaRepository.save(personRecent);

        return PersonaMapper.INSTANCE.personaToPersonaDto(personSaved);
    }

    @Override
    public PersonaDto editarPersona(PersonaDto personaDto) {
        return this.agregarPersona(personaDto);
    }

    @Override
    public boolean eliminarPersona(PersonaDto personaDto) {
        try{
            Persona personRecent = PersonaMapper.INSTANCE.personaDtoToPersona(personaDto);

            if(Objects.nonNull(personRecent)){
                this.personaRepository.delete(personRecent);
                log.info("Persona eliminada correctamente");
                return true;
            }
            return false;
        }catch(Exception ex){
            log.error("Error al eliminar persona: {}", ex.getMessage());
            return false;
        }
    }

    @Override
    public PersonaDto obtenerPersonaPorIdentificacion(String identificacion) {
        Persona personRecover = this.personaRepository.findByIdentificacion(identificacion);
        if(personRecover != null){
            return PersonaMapper.INSTANCE.personaToPersonaDto(personRecover);
        }
        return null;
    }

    @Override
    public PersonaDto obtenerPersonaPorId(Integer idPersona) {
        Persona personRecover = this.personaRepository.findById(idPersona).orElse(null);
        if(personRecover != null){
            return PersonaMapper.INSTANCE.personaToPersonaDto(personRecover);
        }
        return null;
    }

    @Override
    public List<PersonaDto> obtenerTodos() {
        List<Persona> personList = this.personaRepository.findAll();

        return PersonaMapper.INSTANCE.personaListToPersonaDtoList(personList);
    }


}
