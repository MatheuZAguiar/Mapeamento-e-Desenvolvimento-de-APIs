package com.consultorio.app.repository;


import com.consultorio.app.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("SELECT e FROM Consulta e WHERE DATE(e.data) = :data")
    List<Consulta> findByData(@Param("data") LocalDateTime data);

}