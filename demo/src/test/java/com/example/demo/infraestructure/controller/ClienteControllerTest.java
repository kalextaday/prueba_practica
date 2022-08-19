package com.example.demo.infraestructure.controller;


import com.example.demo.application.dto.in.GeneralResponse;
import com.example.demo.application.dto.in.UsuarioDto;
import com.example.demo.application.dto.in.UsuarioDtoDataBuilder;
import com.example.demo.application.services.impl.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ClienteController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ClienteControllerTest {


    @Autowired
    private ClienteController clienteController;

    @MockBean
    private UserService userService;

    @Test
    public void crearUsuario(){
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

        when(userService.createUser(usuarioDto)).thenReturn(usuarioDto);

        ResponseEntity<GeneralResponse<UsuarioDto>> response = this.clienteController.createClient(usuarioDto);
        assertNotNull(response);
        Assertions.assertEquals(201, response.getBody().getCode());

    }

    @Test
    public void editarUsuario(){
        UsuarioDto usuarioDto = new UsuarioDtoDataBuilder()
                .withNombre("Juan")
                .withGenero("M")
                .withEdad(20)
                .withDireccion("Calle falsa 123")
                .withTelefono("123456789")
                .withContrasena("123456")
                .withEstadoCliente(true)
                .build();

        when(userService.updateUser(usuarioDto)).thenReturn(usuarioDto);

        ResponseEntity<GeneralResponse<UsuarioDto>> response = this.clienteController.updateClient(usuarioDto);
        //assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    public void eliminarUsuario(){
        when(userService.deleteUser("1726213976")).thenReturn(true);

        ResponseEntity<GeneralResponse<Boolean>> response = this.clienteController.deleteClient("1726213976");
        Assertions.assertEquals(200, response.getStatusCodeValue());

    }

}