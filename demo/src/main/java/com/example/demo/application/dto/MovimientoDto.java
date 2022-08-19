package com.example.demo.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class MovimientoDto {

    private Integer idMovimiento;

    private Integer idCuenta;

    private Date fecha;

    private String tipoMovimiento;

    private BigDecimal valor;

    private BigDecimal saldo;

    public MovimientoDto(Date fecha, String tipoMovimiento, BigDecimal valor, BigDecimal saldo) {
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldo = saldo;
    }
}
