package com.map.app.service;

import com.map.app.dto.EnderecoDTO;
import com.map.app.entity.Endereco;
import com.map.app.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    private EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
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
        Endereco novoEndereco = new Endereco();
        novoEndereco.setEstado(estado);
        novoEndereco.setCidade(cidade);
        novoEndereco.setRua(rua);
        novoEndereco.setNumero(numero);
        novoEndereco.setCep(cep);

        enderecoRepository.save(novoEndereco);
    }

    public void atualizar(Long id, Endereco endereco) {
        Optional<Endereco> enderecoExistenteOptional = enderecoRepository.findById(id);

        if (enderecoExistenteOptional.isPresent()) {
            Endereco enderecoExistente = enderecoExistenteOptional.get();

            if (endereco.getEstado() != null) {
                enderecoExistente.setEstado(endereco.getEstado());
            }

            if (endereco.getCidade() != null) {
                enderecoExistente.setCidade(endereco.getCidade());
            }
            if (endereco.getRua() != null) {
                enderecoExistente.setRua(endereco.getRua());
            }
            if (endereco.getNumero() != 0) {
                enderecoExistente.setNumero(endereco.getNumero());
            }
            if (endereco.getCep() != null) {
                enderecoExistente.setCep(endereco.getCep());
            }
            enderecoRepository.save(enderecoExistente);
        } else {
            throw new IllegalArgumentException("ID Inválido!");
        }
    }

    public void deleteEndereco(Long id) { Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);

        if (enderecoOptional.isPresent()) {
            enderecoRepository.delete(enderecoOptional.get());
        } else {
            throw new IllegalArgumentException("Endereço não encontrado para o ID informado!");
        }
    }

}
