package com.example.demo.infraestructure.repository;

import com.example.demo.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = "select * from cliente l where l.id_persona= :idPerson and rownum = 1"
            , nativeQuery = true)
    Cliente findByIdPerson(@Param("idPerson") Integer idPerson);

}
