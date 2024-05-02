package com.consultorio.app.service;

import com.consultorio.app.dto.PacienteDTO;
import com.consultorio.app.entity.Endereco;
import com.consultorio.app.entity.Paciente;
import com.consultorio.app.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
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
        } else {
            throw new IllegalArgumentException("ID Inválido!");
        }
    }

    public void deletarPaciente(Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);

        if (pacienteOptional.isPresent()) {
            pacienteRepository.delete(pacienteOptional.get());
        } else {
            throw new IllegalArgumentException("Paciente não encontrado para o ID informado!");
        }
    }
}