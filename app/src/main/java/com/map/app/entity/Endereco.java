package com.map.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_endereco", schema = "public")
public class Endereco {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "cl_estado")
    private String estado;

    @Getter @Setter
    @Column(name = "cl_cidade")
    private String cidade;

    @Getter @Setter
    @Column(name = "cl_rua")
    private String rua;

    @Getter @Setter
    @Column(name = "cl_numero")
    private int numero;

    @Getter @Setter
    @Column(name = "cl_cep")
    private String cep;
}
