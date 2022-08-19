package com.example.demo.application.services.impl;

import com.example.demo.application.dto.ClienteDto;
import com.example.demo.application.dto.PersonaDto;
import com.example.demo.application.dto.in.UsuarioDto;
import com.example.demo.application.port.out.IClientePersistenciaPort;
import com.example.demo.application.port.out.IPersonaPersistenciaPort;
import com.example.demo.application.services.interfaces.IUserService;
import com.example.demo.domain.exception.ErrorInfoResponse;
import com.example.demo.domain.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserService implements IUserService {

    private final IClientePersistenciaPort clientService;
    private final IPersonaPersistenciaPort personService;

    public UserService(IClientePersistenciaPort clientService, IPersonaPersistenciaPort personService) {
        this.clientService = clientService;
        this.personService = personService;
    }

    @Override
    public PersonaDto getObjectPerson(UsuarioDto usuarioDto) {
        if(usuarioDto.getIdentificacion().isEmpty()){
            throw new UserException(new ErrorInfoResponse("Error en la solicitud, Enviar el valor de la identificación", HttpStatus.BAD_REQUEST.value(), ""));
        }

        if(usuarioDto.getNombre().isEmpty()){
            throw new UserException(new ErrorInfoResponse("Error en la solicitud, Enviar el valor del nombre", HttpStatus.BAD_REQUEST.value(), ""));
        }

        return new PersonaDto(
                usuarioDto.getNombre(),
                usuarioDto.getIdentificacion(),
                usuarioDto.getGenero(),
                usuarioDto.getEdad(),
                usuarioDto.getDireccion(),
                usuarioDto.getTelefono()
        );
    }

    @Override
    public ClienteDto getObjectClient(UsuarioDto usuarioDto) {
        if(usuarioDto.getContrasena().isEmpty()){
            throw new UserException(new ErrorInfoResponse("Error en la solicitud, la contraseña debe tener algún valor", HttpStatus.BAD_REQUEST.value(), ""));
        }

        return new ClienteDto(
                usuarioDto.getContrasena(),
                usuarioDto.getEstadoCliente()
        );
    }

    @Override
    public UsuarioDto createUser(UsuarioDto usuarioDto) {
        try{
            PersonaDto personaDto = this.personService.agregarPersona(this.getObjectPerson(usuarioDto));

            if(personaDto != null) {

                ClienteDto clienteDto = this.getObjectClient(usuarioDto);
                clienteDto.setIdPersona(personaDto.getIdPersona());

                ClienteDto clienteSaved = this.clientService.agregarCliente(clienteDto);

                if(clienteSaved != null) {
                    log.info("Cliente creado con exito, id: {}", clienteSaved.getIdCliente());
                    return usuarioDto;
                } else {
                    log.error("No se ha podido crear el cliente");
                    throw new UserException(new ErrorInfoResponse("No se ha podido crear el cliente", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
                }
            } else{
                log.error("No se ha podido crear la persona");
                throw new UserException(new ErrorInfoResponse("No se ha podido crear el usuario", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
            }
        }catch(NullPointerException e){
            log.error("Error al crear el usuario: {}", e.getMessage());
            throw new UserException(new ErrorInfoResponse("Hubo un error al crear el usuario", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
        }
    }

    @Override
    public UsuarioDto updateUser(UsuarioDto usuarioDto) {
        try{
            if(usuarioDto.getIdentificacion().isEmpty()){
                throw new UserException(new ErrorInfoResponse("Error en la solicitud, Enviar el valor de la identificación", HttpStatus.BAD_REQUEST.value(), ""));
            }

            PersonaDto personRecover = this.personService.obtenerPersonaPorIdentificacion(usuarioDto.getIdentificacion());

            if(personRecover != null){

                personRecover.setDireccion(usuarioDto.getDireccion());
                personRecover.setEdad(usuarioDto.getEdad());
                personRecover.setGenero(usuarioDto.getGenero());
                personRecover.setNombre(usuarioDto.getNombre());
                personRecover.setTelefono(usuarioDto.getTelefono());

                ClienteDto clientRecover = this.clientService.obtenerClientePorIdentificacionPersona(personRecover.getIdPersona());

                if(clientRecover != null){
                    clientRecover.setContrasena(usuarioDto.getContrasena());
                    clientRecover.setEstadoCliente(usuarioDto.getEstadoCliente());

                    ClienteDto clienteSaved = this.clientService.editarCliente(clientRecover);

                    if(clienteSaved != null) {

                        PersonaDto personSaved = this.personService.editarPersona(personRecover);

                        if(personSaved != null){
                            log.info("Cliente actualizado con exito, id: {}", clienteSaved.getIdCliente());
                            return usuarioDto;
                        } else{
                            log.error("No se ha podido actualizar la persona");
                            throw new UserException(new ErrorInfoResponse("No se ha podido actualizar el usuario", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
                        }
                    } else {
                        log.error("No se ha podido actualizar el cliente");
                        throw new UserException(new ErrorInfoResponse("No se ha podido actualizar el usuario", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
                    }
                } else{
                    log.error("No hay un cliente asociado con la identificación");
                    throw new UserException(new ErrorInfoResponse("No hay un cliente asociado con la identificacion", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
                }
            } else {
                log.error("No se ha encontrado la persona con la identificación: {}", usuarioDto.getIdentificacion());
                throw new UserException(new ErrorInfoResponse("No hay usuarios registrados con esa identificacion", HttpStatus.BAD_REQUEST.value(), ""));
            }
        }catch(Exception e){
            log.error("Error al actualizar el usuario: {}", e.getMessage());
            throw new UserException(new ErrorInfoResponse("Hubo un error al actualizar el usuario", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
        }
    }

    @Override
    public Boolean deleteUser(String identificacion) {
        try{
            if(identificacion.isEmpty()){
                throw new UserException(new ErrorInfoResponse("Error en la solicitud, Enviar el valor de la identificación", HttpStatus.BAD_REQUEST.value(), ""));
            }

            PersonaDto personaDto = this.personService.obtenerPersonaPorIdentificacion(identificacion);

            if(personaDto != null){

                ClienteDto clienteDto = this.clientService.obtenerClientePorIdentificacionPersona(personaDto.getIdPersona());

                if(clienteDto != null){
                    this.clientService.eliminarCliente(clienteDto);
                    this.personService.eliminarPersona(personaDto);

                    log.info("Cliente eliminado con exito, id: {}", clienteDto.getIdCliente());
                    return true;
                } else {
                    log.error("No se ha podido eliminar el cliente");
                    throw new UserException(new ErrorInfoResponse("No se ha podido eliminar el cliente", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
                }
            } else {
                log.error("No se ha encontrado la persona");
                throw new UserException(new ErrorInfoResponse("No hay usuarios registrados con esa identificación", HttpStatus.BAD_REQUEST.value(), ""));
            }
        }catch(Exception e){
            log.error("Excepcion al eliminar el usuario con identificacion: {}, error: ", identificacion, e.getMessage());
            throw new UserException(new ErrorInfoResponse("Error al eliminar el usuario", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
        }
    }


}
