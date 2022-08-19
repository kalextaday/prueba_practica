package com.example.demo.application.dto.in;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionDto {

    private Integer idTransaction;

    private String numeroCuenta;

    private String tipoCuenta;

    private BigDecimal saldoInicial;

    private Boolean estadoCuenta;

    private String fecha;

    private String tipoMovimiento;

    private BigDecimal valor;

    private BigDecimal saldo;
}
