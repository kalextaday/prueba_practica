package com.example.demo.infraestructure.controller;

import com.example.demo.application.dto.in.AccountDto;
import com.example.demo.application.dto.in.GeneralResponse;
import com.example.demo.application.port.in.ICuentaController;
import com.example.demo.application.port.out.IClientePersistenciaPort;
import com.example.demo.application.port.out.IPersonaPersistenciaPort;
import com.example.demo.application.services.interfaces.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
public class CuentaController implements ICuentaController {

    private final IClientePersistenciaPort clientService;
    private final IPersonaPersistenciaPort personService;
    private final IAccountService accountService;

    public CuentaController(IClientePersistenciaPort clientService, IPersonaPersistenciaPort personService, IAccountService accountService) {

        this.clientService = clientService;
        this.personService = personService;
        this.accountService = accountService;
    }

    @Override
    @PostMapping(value = "/v1/crear")
    public ResponseEntity<GeneralResponse<AccountDto>> createAccount(@RequestBody AccountDto accountDto) {

        GeneralResponse<AccountDto> response = new GeneralResponse<>();
        AccountDto result = this.accountService.createAccount(accountDto);

        response.setCode(HttpStatus.CREATED.value());
        response.setMessage("Cuenta creada correctamente");
        response.setData(result);


        return ResponseEntity
                .created(null)
                .body(response);
    }

    @Override
    @PutMapping(value = "/v1/actualizar")
    public ResponseEntity<GeneralResponse<AccountDto>> updateAccount(@RequestBody AccountDto accountDto) {

        GeneralResponse<AccountDto> response = new GeneralResponse<>();
        AccountDto result = this.accountService.updateAccount(accountDto);

        response.setCode(HttpStatus.OK.value());
        response.setMessage("Cuenta actualizada correctamente");
        response.setData(result);

        return ResponseEntity.ok()
                .body(response);
    }

    @Override
    @DeleteMapping(value = "/v1/eliminar/{numberAccount}")
    public ResponseEntity<GeneralResponse<Boolean>> deleteAccount(@PathVariable String numberAccount) {

        GeneralResponse<Boolean> response = new GeneralResponse<>();
        Boolean result = this.accountService.deleteAccount(numberAccount);

        response.setCode(HttpStatus.OK.value());
        response.setMessage("Cuenta eliminada correctamente");
        response.setData(result);

        return ResponseEntity.ok()
                .body(response);
    }
}
