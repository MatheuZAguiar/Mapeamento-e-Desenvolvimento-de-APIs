package com.consultorio.app.service;

import com.consultorio.app.dto.ConsultaDTO;
import com.consultorio.app.entity.Consulta;
import com.consultorio.app.entity.Paciente;
import com.consultorio.app.repository.ConsultaRepository;
import com.consultorio.app.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    private ConsultaRepository consultaRepository;
    private PacienteRepository pacienteRepository;

    @Autowired
    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public Optional<ConsultaDTO> findById(Long id) {
        return consultaRepository.findById(id).map(ConsultaDTO::new);
    }

    public List<ConsultaDTO> findAll() {
        List<Consulta> consultas = consultaRepository.findAll();
        return consultas.stream().map(ConsultaDTO::new).toList();
    }

    public List<ConsultaDTO> findByData(LocalDateTime data) {
        List<Consulta> consultas = consultaRepository.findByData(data);
        return consultas.stream().map(ConsultaDTO::new).toList();
    }

    public void cadastrarConsulta(Long pacienteId, LocalDateTime data) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

        Consulta novaConsulta = new Consulta();
        novaConsulta.setData(data);
        novaConsulta.setPaciente(paciente);

        consultaRepository.save(novaConsulta);
    }

    public void atualizarConsulta(Long id, LocalDateTime novaData) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(id);

        if (consultaOptional.isPresent()) {
            Consulta consulta = consultaOptional.get();
            consulta.setData(novaData);
            consultaRepository.save(consulta);
        } else {
            throw new IllegalArgumentException("Consulta não encontrada");
        }
    }

    public void deletarConsulta(Long id) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(id);

        if (consultaOptional.isPresent()) {
            consultaRepository.delete(consultaOptional.get());
        } else {
            throw new IllegalArgumentException("Consulta não encontrada");
        }
    }
}