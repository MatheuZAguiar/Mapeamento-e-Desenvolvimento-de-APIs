package com.consultorio.app.service;

import com.consultorio.app.auditing.Audit;
import com.consultorio.app.dto.EnderecoDTO;
import com.consultorio.app.entity.Endereco;
import com.consultorio.app.repository.AuditRepository;
import com.consultorio.app.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    private EnderecoRepository enderecoRepository;
    private AuditRepository auditRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository, AuditRepository auditRepository){
        this.enderecoRepository = enderecoRepository;
        this.auditRepository = auditRepository;
    }

    public Optional<EnderecoDTO> findById(Long id){
        return enderecoRepository.findById(id).map(EnderecoDTO::new);
    }

    public List<EnderecoDTO> findAll(){
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecos.stream().map(EnderecoDTO::new).toList();
    }

    public EnderecoDTO findByEstado(String estado){
        Endereco endereco = enderecoRepository.findByEstado(estado);
        return new EnderecoDTO(endereco);
    }

    public EnderecoDTO findByCidade(String cidade){
        Endereco endereco = enderecoRepository.findByCidade(cidade);
        return new EnderecoDTO(endereco);
    }

    public EnderecoDTO findByRua(String rua){
        Endereco endereco = enderecoRepository.findByRua(rua);
        return new EnderecoDTO(endereco);
    }

    public EnderecoDTO findByNumero(int numero){
        Endereco endereco = enderecoRepository.findByNumero(numero);
        return new EnderecoDTO(endereco);
    }

    public EnderecoDTO findByCEP(String cep){
        Endereco endereco = enderecoRepository.findByCEP(cep);
        return new EnderecoDTO(endereco);
    }

    public void cadastrarEndereco(String estado, String cidade, String rua, int numero, String cep) {
        // Cria um novo objeto Endereco
        Endereco novoEndereco = new Endereco();
        novoEndereco.setEstado(estado);
        novoEndereco.setCidade(cidade);
        novoEndereco.setRua(rua);
        novoEndereco.setNumero(numero);
        novoEndereco.setCep(cep);

        // Salva o novo endereço no repositório de endereços
        enderecoRepository.save(novoEndereco);

        // Registra a operação de auditoria na tabela de auditoria
        Audit audit = new Audit();
        audit.setOperation("CREATE_ENDERECO");
        audit.setCreatedBy(audit.getCreatedBy());
        audit.setCreateDate(audit.getCreateDate());
        auditRepository.save(audit);
    }

    public void atualizar(Long id, Endereco endereco) {
        Optional<Endereco> enderecoExistenteOptional = enderecoRepository.findById(id);

        if (enderecoExistenteOptional.isPresent()) {
            Endereco enderecoExistente = enderecoExistenteOptional.get();

            enderecoExistente.setEstado(endereco.getEstado());
            enderecoExistente.setCidade(endereco.getCidade());
            enderecoExistente.setRua(endereco.getRua());
            enderecoExistente.setNumero(endereco.getNumero());
            enderecoExistente.setCep(endereco.getCep());

            enderecoRepository.save(enderecoExistente);

            // Registra a operação de auditoria na tabela de auditoria
            Audit audit = new Audit();
            audit.setOperation("INSERT_ENDERECO");
            audit.setCreatedBy(audit.getCreatedBy());
            audit.setCreateDate(audit.getCreateDate());
            auditRepository.save(audit);
        } else {
            throw new IllegalArgumentException("ID Inválido!");
        }
    }

    public void deleteEndereco(Long id) { Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);

        if (enderecoOptional.isPresent()) {
            enderecoRepository.delete(enderecoOptional.get());

            // Registra a operação de auditoria na tabela de auditoria
            Audit audit = new Audit();
            audit.setOperation("DELETE_ENDERECO");
            audit.setCreatedBy(audit.getCreatedBy());
            audit.setCreateDate(audit.getCreateDate());
            auditRepository.save(audit);
        } else {
            throw new IllegalArgumentException("Endereço não encontrado para o ID informado!");
        }
    }

}