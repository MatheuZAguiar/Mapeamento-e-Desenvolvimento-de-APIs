package com.map.app.dto;

import com.map.app.entity.Endereco;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class EnderecoDTO {

    private Long id;
    private String estado;
    private String cidade;
    private String rua;
    private int numero;
    private String cep;

    public EnderecoDTO(Endereco endereco){
        id = endereco.getId();
        estado = endereco.getEstado();
        cidade = endereco.getCidade();
        rua = endereco.getRua();
        numero = endereco.getNumero();
        cep = endereco.getCep();
    }

    public EnderecoDTO(Long id, String estado, String cidade, String rua, int numero, String cep) {
        this.id = id;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
    }
}
