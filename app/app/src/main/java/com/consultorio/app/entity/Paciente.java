package com.consultorio.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_paciente", schema = "public")
public class Paciente {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "cl_nome")
    private String nomePaciente;

    @Getter @Setter
    @Column(name = "cl_cpf")
    private String cpf;

    @Getter @Setter
    @Column(name = "cl_email")
    private String email;

    @Getter @Setter
    @Column(name = "cl_telefone")
    private String telefone;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
}