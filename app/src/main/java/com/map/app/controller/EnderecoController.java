package com.map.app.controller;

import com.map.app.dto.EnderecoDTO;
import com.map.app.entity.Endereco;
import com.map.app.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {
    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id) {
        Optional<EnderecoDTO> enderecoDTO = enderecoService.findById(id);
        return enderecoDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> findAll() {
        List<EnderecoDTO> enderecos = enderecoService.findAll();
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<EnderecoDTO> findByEstado(@PathVariable String estado) {
        try {
            EnderecoDTO enderecoDTO = enderecoService.findByEstado(estado);
            return ResponseEntity.ok(enderecoDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<EnderecoDTO> findByCidade(@PathVariable String cidade) {
        try {
            EnderecoDTO enderecoDTO = enderecoService.findByCidade(cidade);
            return ResponseEntity.ok(enderecoDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/rua/{rua}")
    public ResponseEntity<EnderecoDTO> findByRua(@PathVariable String rua) {
        try {
            EnderecoDTO enderecoDTO = enderecoService.findByRua(rua);
            return ResponseEntity.ok(enderecoDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<EnderecoDTO> findByNumero(@PathVariable int numero) {
        try {
            EnderecoDTO enderecoDTO = enderecoService.findByNumero(numero);
            return ResponseEntity.ok(enderecoDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<EnderecoDTO> findByCEP(@PathVariable String cep) {
        try {
            EnderecoDTO enderecoDTO = enderecoService.findByCEP(cep);
            return ResponseEntity.ok(enderecoDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarEndereco(@RequestBody Endereco endereco) {
        try {
            enderecoService.cadastrarEndereco(endereco.getEstado(), endereco.getCidade(), endereco.getRua(),
                    endereco.getNumero(), endereco.getCep());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        try {
            enderecoService.atualizar(id, endereco);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        try {
            enderecoService.deleteEndereco(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}