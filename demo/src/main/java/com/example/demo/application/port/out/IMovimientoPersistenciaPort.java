package com.example.demo.application.port.out;

import com.example.demo.application.dto.MovimientoDto;

import java.util.Date;
import java.util.List;

public interface IMovimientoPersistenciaPort {

    MovimientoDto agregarMovimiento(MovimientoDto personaDto);

    MovimientoDto editarMovimiento(MovimientoDto personaDto);

    boolean eliminarMovimiento(MovimientoDto personaDto);

    List<MovimientoDto> obtenerMovimientoPorFechas(Date startDate, Date endDate);

    MovimientoDto obtenerMovimientoPorId(Integer idMovimiento);

    List<MovimientoDto> obtenerTodos();

    MovimientoDto obtenerUltimoMovimientoPorIdCuenta(Integer idCuenta);

    List<MovimientoDto> obtenerMovimientoDiarioPorCuenta(Date startDate, Integer idCuenta);
}
