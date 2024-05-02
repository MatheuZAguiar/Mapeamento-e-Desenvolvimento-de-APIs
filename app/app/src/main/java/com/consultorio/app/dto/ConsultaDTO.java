package com.consultorio.app.dto;

import com.consultorio.app.entity.Consulta;
import com.consultorio.app.entity.Paciente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ConsultaDTO {

    private Long id;
    private LocalDateTime data;
    private Paciente paciente;

    public ConsultaDTO(Consulta consulta){
        id = consulta.getId();
        data = consulta.getData();
        paciente = consulta.getPaciente();
    }

    public ConsultaDTO(Long id, LocalDateTime data, Paciente paciente) {
        this.id = id;
        this.data = data;
        this.paciente = paciente;
    }
}