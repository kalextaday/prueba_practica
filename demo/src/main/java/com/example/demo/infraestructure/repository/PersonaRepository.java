package com.example.demo.infraestructure.repository;

import com.example.demo.domain.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    @Query(value = "select * from persona l where l.identificacion= :identification and rownum = 1"
            , nativeQuery = true)
    Persona findByIdentificacion(@Param("identification") String identification);
}
