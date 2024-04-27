package com.map.app.repository;


import com.map.app.entity.Endereco;
import com.map.app.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("SELECT e FROM Paciente e WHERE e.nomePaciente = :nomePaciente")
    public Paciente findByNomePaciente(@Param("nomePaciente") String nomePaciente);

    @Query("SELECT e FROM Paciente e WHERE e.cpf = :cpf")
    public Paciente findByCPF(@Param("cpf") String cpf);

    @Query("SELECT e FROM Paciente e WHERE e.email = :email")
    public Paciente findByEmail(@Param("email") String email);

    @Query("SELECT e FROM Paciente e WHERE e.telefone = :telefone")
    public Paciente findByTelefone(@Param("telefone") String telefone);

    @Query("SELECT e FROM Paciente e WHERE e.endereco = :endereco")
    public List<Paciente> findByEndereco(@Param("endereco") Endereco endereco);

}
