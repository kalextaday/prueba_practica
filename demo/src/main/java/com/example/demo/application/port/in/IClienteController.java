package com.example.demo.application.port.in;

import com.example.demo.application.dto.in.GeneralResponse;
import com.example.demo.application.dto.in.UsuarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IClienteController {

    ResponseEntity<GeneralResponse<UsuarioDto>> createClient(@RequestBody UsuarioDto usuarioDto);

    ResponseEntity<GeneralResponse<UsuarioDto>> updateClient(@RequestBody UsuarioDto usuarioDto);

    ResponseEntity<GeneralResponse<Boolean>> deleteClient(@PathVariable String id);

}
