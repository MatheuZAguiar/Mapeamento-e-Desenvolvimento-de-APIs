CREATE TABLE tb_paciente (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    cl_nome VARCHAR(255),
    cl_cpf VARCHAR(14),
    cl_email VARCHAR(255),
    cl_telefone VARCHAR(20),
    endereco_id BIGINT,
    FOREIGN KEY (endereco_id) REFERENCES tb_endereco(id)
);
