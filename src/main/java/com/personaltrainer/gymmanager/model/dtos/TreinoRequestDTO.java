package com.personaltrainer.gymmanager.model.dtos;

import java.time.LocalDate;
import java.util.List;

import com.personaltrainer.gymmanager.model.entidades.Aluno;
import com.personaltrainer.gymmanager.model.entidades.TipoTreino;
import com.personaltrainer.gymmanager.model.enums.TipoEstimulo;

public record TreinoRequestDTO(
    Aluno aluno,
    LocalDate dataCriacao,
    LocalDate dataExpiracao,
    List<TipoTreino> tipoTreinos,
    TipoEstimulo tipoEstimulo
) {
    
}
