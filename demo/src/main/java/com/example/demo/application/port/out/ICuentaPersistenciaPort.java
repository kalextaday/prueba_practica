package com.example.demo.application.port.out;

import com.example.demo.application.dto.CuentaDto;

import java.util.List;

public interface ICuentaPersistenciaPort {

    CuentaDto agregarCuenta(CuentaDto personaDto);

    CuentaDto editarCuenta(CuentaDto personaDto);

    boolean eliminarCuenta(CuentaDto personaDto);

    CuentaDto obtenerCuentaPorNumeroCuenta(String numberAccount);

    CuentaDto obtenerCuentaPorId(Integer idCuenta);

    List<CuentaDto> obtenerTodos();
}
