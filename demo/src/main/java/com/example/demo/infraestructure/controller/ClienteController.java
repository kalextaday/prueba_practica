package com.example.demo.infraestructure.controller;

import com.example.demo.application.dto.in.GeneralResponse;
import com.example.demo.application.dto.in.UsuarioDto;
import com.example.demo.application.port.in.IClienteController;
import com.example.demo.application.services.interfaces.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController implements IClienteController {

    private final IUserService userService;

    public ClienteController(IUserService userService) {

        this.userService = userService;
    }

    @Override
    @ApiOperation("Crear un nuevo usuario")
    @PostMapping(value = "/v1/crear", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GeneralResponse<UsuarioDto>> createClient(@RequestBody UsuarioDto usuarioDto) {

        GeneralResponse<UsuarioDto> response = new GeneralResponse<>();
        UsuarioDto result = this.userService.createUser(usuarioDto);

        response.setCode(HttpStatus.CREATED.value());
        response.setMessage("Usuario creado correctamente");
        response.setData(result);


        return ResponseEntity
                .created(null)
                .body(response);
    }

    @Override
    @ApiOperation("Actualizar un usuario existente")
    @PutMapping(value = "/v1/actualizar" , consumes = "application/json", produces = "application/json")
    public ResponseEntity<GeneralResponse<UsuarioDto>> updateClient(@RequestBody UsuarioDto usuarioDto) {

        GeneralResponse<UsuarioDto> response = new GeneralResponse<>();
        UsuarioDto result = this.userService.updateUser(usuarioDto);

        response.setCode(HttpStatus.OK.value());
        response.setMessage("Usuario actualizado correctamente");
        response.setData(result);

        return ResponseEntity.ok()
                .body(response);
    }

    @Override
    @ApiOperation("Eliminar un usuario")
    @DeleteMapping(value = "/v1/eliminar/{identificacion}")
    public ResponseEntity<GeneralResponse<Boolean>> deleteClient(@PathVariable String identificacion) {
        GeneralResponse<Boolean> response = new GeneralResponse<>();
        Boolean result = this.userService.deleteUser(identificacion);

        response.setCode(HttpStatus.OK.value());
        response.setMessage("Usuario eliminado correctamente");
        response.setData(result);

        return ResponseEntity.ok()
                .body(response);
    }
}
