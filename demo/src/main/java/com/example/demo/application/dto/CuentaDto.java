package com.example.demo.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CuentaDto {

    private Integer idCuenta;

    private Integer idCliente;

    private String numeroCuenta;

    private String tipoCuenta;

    private BigDecimal saldoInicial;

    private Boolean estadoCuenta;

    public CuentaDto(Integer idCliente, String numeroCuenta, String tipoCuenta, BigDecimal saldoInicial, Boolean estadoCuenta) {
        this.idCliente = idCliente;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estadoCuenta = estadoCuenta;
    }

    public CuentaDto(String numeroCuenta, String tipoCuenta, BigDecimal saldoInicial, Boolean estadoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estadoCuenta = estadoCuenta;
    }
}
