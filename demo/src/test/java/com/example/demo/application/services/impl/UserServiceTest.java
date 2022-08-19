package com.example.demo.application.services.impl;

import com.example.demo.application.dto.ClienteDto;
import com.example.demo.application.dto.ClienteDtoDataBuilder;
import com.example.demo.application.dto.PersonaDto;
import com.example.demo.application.dto.PersonaDtoDataBuilder;
import com.example.demo.application.dto.in.UsuarioDto;
import com.example.demo.application.dto.in.UsuarioDtoDataBuilder;
import com.example.demo.application.port.impl.ClienteService;
import com.example.demo.application.port.impl.PersonaService;
import com.example.demo.domain.exception.UserException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UserService.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private ClienteService clientService;

    @MockBean
    private PersonaService personaService;

    @Test
    public void crearUsuarioExitoso(){
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

        PersonaDto personaDto = new PersonaDtoDataBuilder()
                .withNombre("Juan")
                .withIdentificacion("123456789")
                .withGenero("M")
                .withEdad(20)
                .withDireccion("Calle falsa 123")
                .withTelefono("123456789")
                .build();

        ClienteDto clienteDto = new ClienteDtoDataBuilder()
                .withContrasena("123456")
                .withEstadoCliente(true)
                .build();

        when(personaService.agregarPersona(personaDto)).thenReturn(personaDto);

        when(clientService.agregarCliente(clienteDto)).thenReturn(clienteDto);

        UsuarioDto result = this.userService.createUser(usuarioDto);

        assertNotNull(result);
    }

    @Test(expected = UserException.class)
    public void crearUsuarioFallidoPorPersona(){
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

        PersonaDto personaDto = new PersonaDtoDataBuilder()
                .withNombre("Juan")
                .withIdentificacion("123456789")
                .withGenero("M")
                .withEdad(20)
                .withDireccion("Calle falsa 123")
                .withTelefono("123456789")
                .build();

        when(personaService.agregarPersona(personaDto)).thenReturn(null);

        UsuarioDto result = this.userService.createUser(usuarioDto);
    }

    @Test(expected = UserException.class)
    public void crearUsuarioFallidoPorCliente(){
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

        PersonaDto personaDto = new PersonaDtoDataBuilder()
                .withNombre("Juan")
                .withIdentificacion("123456789")
                .withGenero("M")
                .withEdad(20)
                .withDireccion("Calle falsa 123")
                .withTelefono("123456789")
                .build();

        ClienteDto clienteDto = new ClienteDtoDataBuilder()
                .withContrasena("123456")
                .withEstadoCliente(true)
                .build();

        when(personaService.agregarPersona(personaDto)).thenReturn(personaDto);

        when(clientService.agregarCliente(clienteDto)).thenReturn(null);

        UsuarioDto result = this.userService.createUser(usuarioDto);
    }

}