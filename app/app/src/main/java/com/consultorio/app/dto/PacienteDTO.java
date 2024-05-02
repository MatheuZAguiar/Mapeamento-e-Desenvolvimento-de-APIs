package com.consultorio.app.dto;

import com.consultorio.app.entity.Endereco;
import com.consultorio.app.entity.Paciente;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PacienteDTO {

    private Long id;
    private String nomePaciente;
    private String cpf;
    private String email;
    private String telefone;
    private Endereco endereco;

    public PacienteDTO(Paciente paciente){
        id = paciente.getId();
        nomePaciente = paciente.getNomePaciente();
        cpf = paciente.getCpf();
        email = paciente.getEmail();
        telefone = paciente.getTelefone();
        endereco = paciente.getEndereco();
    }

    public PacienteDTO(Long id, String nomePaciente, String cpf, String email, String telefone, Endereco endereco) {
        this.id = id;
        this.nomePaciente = nomePaciente;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }
}