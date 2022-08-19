package com.example.demo.application.port.impl;

import com.example.demo.application.dto.MovimientoDto;
import com.example.demo.application.mapper.MovimientoMapper;
import com.example.demo.application.port.out.IMovimientoPersistenciaPort;
import com.example.demo.domain.entity.Movimiento;
import com.example.demo.infraestructure.repository.MovimientoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class MovimientoService implements IMovimientoPersistenciaPort {

    private final MovimientoRepository movimientoRepository;

    public MovimientoService(MovimientoRepository movimientoRepository) {
        
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    public MovimientoDto agregarMovimiento(MovimientoDto movimientoDto) {
        Movimiento transactionRecent = MovimientoMapper.INSTANCE.movimientoDtoToMovimiento(movimientoDto);
        Movimiento transactionSaved = this.movimientoRepository.save(transactionRecent);

        return MovimientoMapper.INSTANCE.movimientoToMovimientoDto(transactionSaved);
    }

    @Override
    public MovimientoDto editarMovimiento(MovimientoDto movimientoDto) {
        return this.agregarMovimiento(movimientoDto);
    }

    @Override
    public boolean eliminarMovimiento(MovimientoDto movimientoDto) {
        try{
            Movimiento transactionRecent = MovimientoMapper.INSTANCE.movimientoDtoToMovimiento(movimientoDto);

            if(Objects.nonNull(transactionRecent)){
                this.movimientoRepository.delete(transactionRecent);
                log.info("Movimiento eliminado correctamente");
                return true;
            }
            return false;
        }catch(Exception ex){
            log.error("Error al eliminar movimiento: {}", ex.getMessage());
            return false;
        }
    }

    @Override
    public List<MovimientoDto> obtenerMovimientoPorFechas(Date startDate, Date endDate) {
        List<Movimiento> transactionRecoverList = this.movimientoRepository.getMovementsByRangeDate(startDate, endDate);
        return MovimientoMapper.INSTANCE.movimientoListToMovimientoDtoList(transactionRecoverList);
    }

    @Override
    public MovimientoDto obtenerMovimientoPorId(Integer idMovimiento) {
        Movimiento transactionRecover = this.movimientoRepository.findById(idMovimiento).orElse(null);
        if(transactionRecover != null){
            return MovimientoMapper.INSTANCE.movimientoToMovimientoDto(transactionRecover);
        }
        return null;
    }

    @Override
    public List<MovimientoDto> obtenerTodos() {
        List<Movimiento> transactionList = this.movimientoRepository.findAll();

        return MovimientoMapper.INSTANCE.movimientoListToMovimientoDtoList(transactionList);
    }

    @Override
    public MovimientoDto obtenerUltimoMovimientoPorIdCuenta(Integer idCuenta) {
        Movimiento transactionLast = this.movimientoRepository.getLastMovementByIdAccount(idCuenta);

        return MovimientoMapper.INSTANCE.movimientoToMovimientoDto(transactionLast);
    }

    @Override
    public List<MovimientoDto> obtenerMovimientoDiarioPorCuenta(Date startDate, Integer idCuenta) {
        List<Movimiento> transactionRecoverList = this.movimientoRepository.getMovementsByRangeDateAndIdCuenta(startDate, idCuenta);
        return MovimientoMapper.INSTANCE.movimientoListToMovimientoDtoList(transactionRecoverList);
    }
}
