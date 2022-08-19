package com.example.demo.application.services.interfaces;

import com.example.demo.application.dto.ClienteDto;
import com.example.demo.application.dto.PersonaDto;
import com.example.demo.application.dto.in.UsuarioDto;

public interface IUserService {

    PersonaDto getObjectPerson(UsuarioDto usuarioDto);

    ClienteDto getObjectClient(UsuarioDto usuarioDto);

    UsuarioDto createUser(UsuarioDto usuarioDto);

    UsuarioDto updateUser(UsuarioDto usuarioDto);

    Boolean deleteUser(String identificacion);


}
