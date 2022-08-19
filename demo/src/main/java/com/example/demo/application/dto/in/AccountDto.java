package com.example.demo.application.dto.in;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AccountDto {

    private String nombre;

    private String identificacion;

    private String numeroCuenta;

    private String tipoCuenta;

    private BigDecimal saldoInicial;

    private Boolean estadoCuenta;
}
