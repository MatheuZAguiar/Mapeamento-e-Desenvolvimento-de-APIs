package com.consultorio.app.controller;

import com.consultorio.app.dto.ConsultaDTO;
import com.consultorio.app.entity.Consulta;
import com.consultorio.app.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {
    private final ConsultaService consultaService;

    @Autowired
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> findById(@PathVariable Long id) {
        Optional<ConsultaDTO> consultaDTO = consultaService.findById(id);
        return consultaDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> findAll() {
        List<ConsultaDTO> consultas = consultaService.findAll();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<List<ConsultaDTO>> findByData(@PathVariable LocalDateTime data) {
        try {
            List<ConsultaDTO> consultas = consultaService.findByData(data);
            return ResponseEntity.ok(consultas);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarConsulta(@RequestBody Consulta consulta) {
        try {
            consultaService.cadastrarConsulta(consulta.getPaciente().getId(), consulta.getData());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
        try {
            consultaService.atualizarConsulta(id, consulta.getData());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable Long id) {
        try {
            consultaService.deletarConsulta(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}