package com.example.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "movimiento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovimiento;

    private Integer idCuenta;

    private Date fecha;

    private String tipoMovimiento;

    private BigDecimal valor;

    private BigDecimal saldo;
}
