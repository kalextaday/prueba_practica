package com.example.demo.infraestructure.repository;

import com.example.demo.domain.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

    @Query(value = "select * from movimiento m where m.fecha>= :startDate and m.fecha<= :endDate"
            , nativeQuery = true)
    List<Movimiento> getMovementsByRangeDate(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDate,
                                             @Param("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDate);

    @Query(value = "select * from movimiento m where m.id_cuenta= :idCuenta order by m.id_movimiento desc limit 1"
            , nativeQuery = true)
    Movimiento getLastMovementByIdAccount(@Param("idCuenta") Integer idCuenta);

    @Query(value = "select * from movimiento m where m.fecha= :startDate and m.id_cuenta= :idCuenta and tipo_movimiento = 'debito'"
            , nativeQuery = true)
    List<Movimiento> getMovementsByRangeDateAndIdCuenta(@Param("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDate,
                                             @Param("idCuenta") Integer idCuenta);
}
