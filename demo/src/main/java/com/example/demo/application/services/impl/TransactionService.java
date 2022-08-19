package com.example.demo.application.services.impl;

import com.example.demo.application.dto.CuentaDto;
import com.example.demo.application.dto.MovimientoDto;
import com.example.demo.application.dto.in.AccountDto;
import com.example.demo.application.dto.in.GeneralResponse;
import com.example.demo.application.dto.in.TransactionDto;
import com.example.demo.application.port.out.IClientePersistenciaPort;
import com.example.demo.application.port.out.ICuentaPersistenciaPort;
import com.example.demo.application.port.out.IMovimientoPersistenciaPort;
import com.example.demo.application.port.out.IPersonaPersistenciaPort;
import com.example.demo.application.services.interfaces.ITransactionService;
import com.example.demo.domain.enums.EnumTypeTransaction;
import com.example.demo.domain.exception.AccountException;
import com.example.demo.domain.exception.ErrorInfoResponse;
import com.example.demo.domain.exception.TransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TransactionService implements ITransactionService {

    private final ICuentaPersistenciaPort cuentaService;
    private final IClientePersistenciaPort clientService;
    private final IPersonaPersistenciaPort personService;
    private final IMovimientoPersistenciaPort movimientoService;
    private final Double LIMIT_AMOUNT_DAILY = 1000.0;

    public TransactionService(ICuentaPersistenciaPort cuentaService, IClientePersistenciaPort clientService, IPersonaPersistenciaPort personService, IMovimientoPersistenciaPort movimientoService) {
        this.cuentaService = cuentaService;
        this.clientService = clientService;
        this.personService = personService;
        this.movimientoService = movimientoService;
    }

    public MovimientoDto getObjectMovimiento(TransactionDto transactionDto) throws ParseException {
        if(transactionDto.getNumeroCuenta().isEmpty()){
            throw new AccountException(new ErrorInfoResponse("Error en la solicitud, Enviar el numero de cuenta", HttpStatus.BAD_REQUEST.value(), ""));
        }

        SimpleDateFormat formatIn = new SimpleDateFormat("dd/MM/yyyy");

        return new MovimientoDto(
                formatIn.parse(transactionDto.getFecha()),
                transactionDto.getTipoMovimiento(),
                transactionDto.getValor(),
                transactionDto.getSaldo()
        );
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        try{
            CuentaDto accountRecover = this.cuentaService.obtenerCuentaPorNumeroCuenta(transactionDto.getNumeroCuenta());

            if(accountRecover != null){
                MovimientoDto movementNew = this.getObjectMovimiento(transactionDto);
                movementNew.setIdCuenta(accountRecover.getIdCuenta());

                MovimientoDto movementLast = this.movimientoService.obtenerUltimoMovimientoPorIdCuenta(accountRecover.getIdCuenta());

                BigDecimal balance = movementLast != null ? movementLast.getSaldo() : accountRecover.getSaldoInicial();
                BigDecimal valueTransaction = movementNew.getValor();

                if(movementNew.getTipoMovimiento().toUpperCase().equals(EnumTypeTransaction.CREDITO.getType())) {
                    movementNew.setSaldo(balance.add(valueTransaction));
                } else if(movementNew.getTipoMovimiento().toUpperCase().equals(EnumTypeTransaction.DEBITO.getType())) {
                    if(balance.compareTo(valueTransaction) >= 0){
                        SimpleDateFormat formatIn = new SimpleDateFormat("dd/MM/yyyy");
                        List<MovimientoDto> movementsDay = this.movimientoService
                                .obtenerMovimientoDiarioPorCuenta(formatIn.parse(transactionDto.getFecha()), accountRecover.getIdCuenta());

                        if(movementsDay.stream().mapToDouble(mv->mv.getValor().doubleValue()).sum() <= LIMIT_AMOUNT_DAILY){
                            movementNew.setSaldo(balance.subtract(movementNew.getValor()));
                        } else{
                            throw new TransactionException(new ErrorInfoResponse("Cupo Diario Excedido", HttpStatus.BAD_REQUEST.value(), ""));
                        }
                    } else{
                        throw new TransactionException(new ErrorInfoResponse("Saldo no disponible", HttpStatus.BAD_REQUEST.value(), ""));
                    }
                }

                MovimientoDto movementSaved = this.movimientoService.agregarMovimiento(movementNew);

                if(movementSaved != null){
                    log.info("Movimiento creado con exito, id: {}", movementSaved.getIdMovimiento());
                    transactionDto.setIdTransaction(movementSaved.getIdMovimiento());
                    transactionDto.setSaldo(movementSaved.getSaldo());
                    transactionDto.setTipoCuenta(accountRecover.getTipoCuenta());
                    transactionDto.setSaldoInicial(accountRecover.getSaldoInicial());
                    transactionDto.setEstadoCuenta(accountRecover.getEstadoCuenta());

                    return transactionDto;
                }else{
                    log.error("No se ha podido registrar el Movimiento");
                    throw new TransactionException(new ErrorInfoResponse("No se ha podido registrar el Movimiento", HttpStatus.BAD_REQUEST.value(), ""));
                }
            } else{
                log.error("No se ha podido encontrar la cuenta");
                throw new TransactionException(new ErrorInfoResponse("No hay cuentas registradas con ese numero de cuenta", HttpStatus.BAD_REQUEST.value(), ""));
            }
        } catch (ParseException e){
            log.error("Error al crear el movimiento: {}", e.getMessage());
            throw new TransactionException(new ErrorInfoResponse("Error al crear el movimiento", HttpStatus.BAD_REQUEST.value(), ""));
        }
    }

    @Override
    public TransactionDto updateTransaction(TransactionDto transactionDto) {

        try{
            MovimientoDto movementRecover = this.movimientoService.obtenerMovimientoPorId(transactionDto.getIdTransaction());


            if(movementRecover != null){
                SimpleDateFormat formatIn = new SimpleDateFormat("dd/MM/yyyy");

                movementRecover.setFecha(formatIn.parse(transactionDto.getFecha()) != null ? formatIn.parse(transactionDto.getFecha()) : movementRecover.getFecha());
                movementRecover.setTipoMovimiento(transactionDto.getTipoMovimiento() != null ? transactionDto.getTipoMovimiento() : movementRecover.getTipoMovimiento());
                movementRecover.setValor(transactionDto.getValor() != null ? transactionDto.getValor() : movementRecover.getValor());
                movementRecover.setSaldo(transactionDto.getSaldo() != null ? transactionDto.getSaldo() : movementRecover.getSaldo());


                MovimientoDto movementSaved = this.movimientoService.editarMovimiento(movementRecover);

                if(movementSaved != null){
                    log.info("Movimiento editado con exito, id: {}", movementSaved.getIdMovimiento());
                    transactionDto.setSaldo(movementSaved.getSaldo());
                    return transactionDto;
                }else{
                    log.error("No se ha podido editar el Movimiento");
                    throw new TransactionException(new ErrorInfoResponse("No se ha podido editar el Movimiento", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
                }
            } else{
                log.error("No se ha podido encontrar el movimiento con id: {}", transactionDto.getIdTransaction());
                throw new TransactionException(new ErrorInfoResponse("No hay movimientos registrados con ese idTransaction", HttpStatus.BAD_REQUEST.value(), ""));
            }
        }catch(ParseException e){
            log.error("Error al editar el movimiento: {}", e.getMessage());
            throw new TransactionException(new ErrorInfoResponse("Error al editar el movimiento", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
        }
    }

    @Override
    public Boolean deleteTransaction(Integer idTransaction) {
        try{
            MovimientoDto movementRecover = this.movimientoService.obtenerMovimientoPorId(idTransaction);

            if(movementRecover != null) {
                if(this.movimientoService.eliminarMovimiento(movementRecover)){
                    log.info("Movimiento eliminado con exito, id: {}", movementRecover.getIdMovimiento());
                    return true;
                } else{
                    log.error("No se ha podido eliminar el Movimiento");
                    throw new TransactionException(new ErrorInfoResponse("No se ha podido eliminar el Movimiento", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
                }
            } else{
                log.error("No se ha podido encontrar el movimiento con id: {}", idTransaction);
                throw new TransactionException(new ErrorInfoResponse("No hay movimientos registrados con esa identificación", HttpStatus.BAD_REQUEST.value(), ""));
            }
        }catch(NullPointerException e){
            log.error("Error al eliminar el movimiento: {}", e.getMessage());
            throw new TransactionException(new ErrorInfoResponse("Error al eliminar el movimiento", HttpStatus.INTERNAL_SERVER_ERROR.value(), ""));
        }
    }

    @Override
    public GeneralResponse generateReportByDates(String dateInit, String dateEnd) {
        SimpleDateFormat formatIn = new SimpleDateFormat("dd-MM-yyyy");
        try{
            List<MovimientoDto> movements = this.movimientoService.obtenerMovimientoPorFechas(formatIn.parse(dateInit), formatIn.parse(dateEnd));
            return new GeneralResponse(
                    HttpStatus.OK.value(),
                    "Consulta realizada con exito",
                    movements
            );
        }catch(Exception e){
            log.error("Error al convertir la fecha");
            throw new TransactionException(new ErrorInfoResponse("Error al convertir la fecha", HttpStatus.BAD_REQUEST.value(), ""));
        }

    }
}
