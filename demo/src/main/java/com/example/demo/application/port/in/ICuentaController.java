package com.example.demo.application.port.in;

import com.example.demo.application.dto.in.AccountDto;
import com.example.demo.application.dto.in.GeneralResponse;
import com.example.demo.application.dto.in.UsuarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICuentaController {

    ResponseEntity<GeneralResponse<AccountDto>> createAccount(@RequestBody AccountDto accountDto);

    ResponseEntity<GeneralResponse<AccountDto>> updateAccount(@RequestBody AccountDto accountDto);

    ResponseEntity<GeneralResponse<Boolean>> deleteAccount(@PathVariable String numberAccount);
}
