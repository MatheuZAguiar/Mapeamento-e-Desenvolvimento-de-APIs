package com.map.app.repository;

import com.map.app.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("SELECT e FROM Endereco e WHERE e.estado = :estado")
    public Endereco findByEstado(@Param("estado") String estado);

    @Query("SELECT e FROM Endereco e WHERE e.cidade = :cidade")
    public Endereco findByCidade(@Param("cidade") String cidade);

    @Query("SELECT e FROM Endereco e WHERE e.rua = :rua")
    public Endereco findByRua(@Param("rua") String rua);

    @Query("SELECT e FROM Endereco e WHERE e.numero = :numero")
    public Endereco findByNumero(@Param("numero") int numero);

    @Query("SELECT e FROM Endereco e WHERE e.cep = :cep")
    public Endereco findByCEP(@Param("cep") String cep);
}
