package com.example.demo.infraestructure.repository;

import com.example.demo.domain.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

    @Query(value = "select * from cuenta l where l.numero_cuenta= :numberAccount and rownum = 1"
            , nativeQuery = true)
    Cuenta findByNumberAccount(@Param("numberAccount") String numberAccount);
}
