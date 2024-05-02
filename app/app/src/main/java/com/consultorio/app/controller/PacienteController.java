package com.consultorio.app.controller;

import com.consultorio.app.dto.PacienteDTO;
import com.consultorio.app.entity.Paciente;
import com.consultorio.app.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {
    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> findById(@PathVariable Long id) {
        Optional<PacienteDTO> pacienteDTO = pacienteService.findById(id);
        return pacienteDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> findAll() {
        List<PacienteDTO> pacientes = pacienteService.findAll();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<PacienteDTO> findByNome(@PathVariable String nome) {
        try {
            PacienteDTO pacienteDTO = pacienteService.findByNome(nome);
            return ResponseEntity.ok(pacienteDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PacienteDTO> findByCPF(@PathVariable String cpf) {
        try {
            PacienteDTO pacienteDTO = pacienteService.findByCPF(cpf);
            return ResponseEntity.ok(pacienteDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<PacienteDTO> findByEmail(@PathVariable String email) {
        try {
            PacienteDTO pacienteDTO = pacienteService.findByEmail(email);
            return ResponseEntity.ok(pacienteDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/telefone/{telefone}")
    public ResponseEntity<PacienteDTO> findByTelefone(@PathVariable String telefone) {
        try {
            PacienteDTO pacienteDTO = pacienteService.findByTelefone(telefone);
            return ResponseEntity.ok(pacienteDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarPaciente(@RequestBody Paciente paciente) {
        try {
            pacienteService.cadastrarPaciente(paciente.getNomePaciente(), paciente.getCpf(), paciente.getEmail(),
                    paciente.getTelefone(), paciente.getEndereco());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        try {
            pacienteService.atualizarPaciente(id, paciente);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        try {
            pacienteService.deletarPaciente(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}