package com.example.demo.application.services.impl;

import com.example.demo.application.dto.ClienteDto;
import com.example.demo.application.dto.CuentaDto;
import com.example.demo.application.dto.PersonaDto;
import com.example.demo.application.dto.in.AccountDto;
import com.example.demo.application.dto.in.GeneralResponse;
import com.example.demo.application.port.out.IClientePersistenciaPort;
import com.example.demo.application.port.out.ICuentaPersistenciaPort;
import com.example.demo.application.port.out.IPersonaPersistenciaPort;
import com.example.demo.application.services.interfaces.IAccountService;
import com.example.demo.domain.exception.AccountException;
import com.example.demo.domain.exception.ErrorInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService implements IAccountService {

    private final ICuentaPersistenciaPort cuentaService;
    private final IClientePersistenciaPort clientService;
    private final IPersonaPersistenciaPort personService;

    public AccountService(ICuentaPersistenciaPort cuentaService, IClientePersistenciaPort clientService, IPersonaPersistenciaPort personService) {
        this.cuentaService = cuentaService;
        this.clientService = clientService;
        this.personService = personService;
    }

    public CuentaDto getObjectAccount(AccountDto accountDto) {
        if(accountDto.getNumeroCuenta().isEmpty()){
            throw new AccountException(new ErrorInfoResponse("Error en la solicitud, Enviar el numero de cuenta", HttpStatus.BAD_REQUEST.value(), ""));
        }

        return new CuentaDto(
                accountDto.getNumeroCuenta(),
                accountDto.getTipoCuenta(),
                accountDto.getSaldoInicial(),
                accountDto.getEstadoCuenta()
        );
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        try{
            if(accountDto.getIdentificacion().isEmpty()){
                throw new AccountException(new ErrorInfoResponse("Error en la solicitud, Enviar el valor de la identificación", HttpStatus.BAD_REQUEST.value(), ""));
            }

            PersonaDto personRecover = this.personService.obtenerPersonaPorIdentificacion(accountDto.getIdentificacion());

            if(personRecover != null){
                ClienteDto clientRecover = this.clientService.obtenerClientePorIdentificacionPersona(personRecover.getIdPersona());

                CuentaDto cuentaDto = this.getObjectAccount(accountDto);
                cuentaDto.setIdCliente(clientRecover.getIdCliente());

                CuentaDto cuentaSaved = this.cuentaService.agregarCuenta(cuentaDto);

                if(cuentaSaved != null){
                    log.info("Cuenta creada con exito, id: {}", cuentaSaved.getIdCliente());
                    return accountDto;
                } else {
                    log.error("No se ha podido crear el cliente");
                    throw new AccountException(new ErrorInfoResponse("No se ha podido actualizar la cuenta", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
                }
            } else{
                log.error("No se ha encontrado la persona con la identificación: {}", accountDto.getIdentificacion());
                throw new AccountException(new ErrorInfoResponse("No se ha encontrado al usuario con esa identificación", HttpStatus.BAD_REQUEST.value(), ""));
            }
        }catch(Exception e){
            log.error("Error al crear la cuenta: {}", e.getMessage());
            throw new AccountException(new ErrorInfoResponse("Error al crear la cuenta", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
        }
    }

    @Override
    public AccountDto updateAccount(AccountDto accountDto) {
        if(accountDto.getIdentificacion().isEmpty()){
            throw new AccountException(new ErrorInfoResponse("Error en la solicitud, Enviar el valor de la identificación", HttpStatus.BAD_REQUEST.value(), ""));
        }
        PersonaDto personRecover = this.personService.obtenerPersonaPorIdentificacion(accountDto.getIdentificacion());

        if(personRecover != null){
            ClienteDto clientRecover = this.clientService.obtenerClientePorIdentificacionPersona(personRecover.getIdPersona());

            CuentaDto accountRecover = this.cuentaService.obtenerCuentaPorNumeroCuenta(accountDto.getNumeroCuenta());

            if (accountRecover != null) {
                if(accountRecover.getIdCliente() == clientRecover.getIdCliente()){
                    accountRecover.setEstadoCuenta(accountDto.getEstadoCuenta());
                    accountRecover.setSaldoInicial(accountDto.getSaldoInicial());
                    accountRecover.setTipoCuenta(accountDto.getTipoCuenta());

                    CuentaDto cuentaSaved = this.cuentaService.agregarCuenta(accountRecover);

                    if(cuentaSaved != null){
                        log.info("Cuenta actualizada con exito, id: {}", cuentaSaved.getIdCuenta());

                        return accountDto;
                    } else {
                        log.error("No se ha podido actualizar la cuenta");
                        throw new AccountException(new ErrorInfoResponse("No se ha podido actualizar la cuenta", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
                    }
                }else{
                    log.error("La cuenta no pertenece al cliente con identificacion: {}", accountDto.getIdentificacion());
                    throw new AccountException(new ErrorInfoResponse("La cuenta no pertenece al cliente", HttpStatus.BAD_REQUEST.value(), ""));
                }
            }else{
                log.error("No existe cuenta con ese numero de cuenta: {}", accountDto.getNumeroCuenta());
                throw new AccountException(new ErrorInfoResponse("No existe cuenta con ese numero de cuenta", HttpStatus.BAD_REQUEST.value(), ""));
            }

        } else{
            log.error("No se ha encontrado la persona con la identificación: {}", accountDto.getIdentificacion());
            throw new AccountException(new ErrorInfoResponse("No hay usuarios registrados con esa identificacion", HttpStatus.BAD_REQUEST.value(), ""));
        }
    }

    @Override
    public Boolean deleteAccount(String numberAccount) {
        try{
            CuentaDto accountRecover = this.cuentaService.obtenerCuentaPorNumeroCuenta(numberAccount);
            if(accountRecover != null){
                if(this.cuentaService.eliminarCuenta(accountRecover)){
                    return true;
                } else{
                    throw new AccountException(new ErrorInfoResponse("No se ha podido eliminar la cuenta", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
                }
            }else{
                log.error("No se ha encontrado alguna cuenta con el numero: {}", numberAccount);
                throw new AccountException(new ErrorInfoResponse("No hay cuentas registradas con esa identificacion", HttpStatus.BAD_REQUEST.value(), ""));
            }
        }catch(NullPointerException e){
            log.error("No se ha podido eliminar la cuenta");
            throw new AccountException(new ErrorInfoResponse("No se ha podido eliminar la cuenta", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
        }
    }
}
