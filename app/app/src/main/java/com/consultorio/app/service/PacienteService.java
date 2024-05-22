package com.consultorio.app.service;

import com.consultorio.app.auditing.Audit;
import com.consultorio.app.dto.PacienteDTO;
import com.consultorio.app.entity.Endereco;
import com.consultorio.app.entity.Paciente;
import com.consultorio.app.repository.AuditRepository;
import com.consultorio.app.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;
    private AuditRepository auditRepository;
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, AuditRepository auditRepository) {
        this.pacienteRepository = pacienteRepository;
        this.auditRepository = auditRepository;
    }

    public Optional<PacienteDTO> findById(Long id) {
        return pacienteRepository.findById(id).map(PacienteDTO::new);
    }

    public List<PacienteDTO> findAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(PacienteDTO::new).toList();
    }

    public PacienteDTO findByNome(String nome) {
        Paciente paciente = pacienteRepository.findByNomePaciente(nome);
        return new PacienteDTO(paciente);
    }

    public PacienteDTO findByCPF(String cpf) {
        Paciente paciente = pacienteRepository.findByCPF(cpf);
        return new PacienteDTO(paciente);
    }

    public PacienteDTO findByEmail(String email) {
        Paciente paciente = pacienteRepository.findByEmail(email);
        return new PacienteDTO(paciente);
    }

    public PacienteDTO findByTelefone(String telefone) {
        Paciente paciente = pacienteRepository.findByTelefone(telefone);
        return new PacienteDTO(paciente);
    }

    public void cadastrarPaciente(String nome, String cpf, String email, String telefone, Endereco endereco) {
        Paciente novoPaciente = new Paciente();
        novoPaciente.setNomePaciente(nome);
        novoPaciente.setCpf(cpf);
        novoPaciente.setEmail(email);
        novoPaciente.setTelefone(telefone);
        novoPaciente.setEndereco(endereco);

        pacienteRepository.save(novoPaciente);

        // Registra a operação de auditoria na tabela de auditoria
        Audit audit = new Audit();
        audit.setOperation("CREATE_PACIENTE");
        audit.setCreatedBy(audit.getCreatedBy());
        audit.setCreateDate(audit.getCreateDate());
        auditRepository.save(audit);
    }

    public void atualizarPaciente(Long id, Paciente paciente) {
        Optional<Paciente> pacienteExistenteOptional = pacienteRepository.findById(id);

        if (pacienteExistenteOptional.isPresent()) {
            Paciente pacienteExistente = pacienteExistenteOptional.get();

            if (paciente.getNomePaciente() != null) {
                pacienteExistente.setNomePaciente(paciente.getNomePaciente());
            }

            if (paciente.getCpf() != null) {
                pacienteExistente.setCpf(paciente.getCpf());
            }

            if (paciente.getEmail() != null) {
                pacienteExistente.setEmail(paciente.getEmail());
            }

            if (paciente.getTelefone() != null) {
                pacienteExistente.setTelefone(paciente.getTelefone());
            }

            if (paciente.getEndereco() != null) {
                pacienteExistente.setEndereco(paciente.getEndereco());
            }

            pacienteRepository.save(pacienteExistente);

            // Registra a operação de auditoria na tabela de auditoria
            Audit audit = new Audit();
            audit.setOperation("INSERT_PACIENTE");
            audit.setCreatedBy(audit.getCreatedBy());
            audit.setCreateDate(audit.getCreateDate());
            auditRepository.save(audit);
        } else {
            throw new IllegalArgumentException("ID Inválido!");
        }
    }

    public void deletarPaciente(Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);

        if (pacienteOptional.isPresent()) {
            pacienteRepository.delete(pacienteOptional.get());

            // Registra a operação de auditoria na tabela de auditoria
            Audit audit = new Audit();
            audit.setOperation("DELETE_PACIENTE");
            audit.setCreatedBy(audit.getCreatedBy());
            audit.setCreateDate(audit.getCreateDate());
            auditRepository.save(audit);
        } else {
            throw new IllegalArgumentException("Paciente não encontrado para o ID informado!");
        }
    }
}