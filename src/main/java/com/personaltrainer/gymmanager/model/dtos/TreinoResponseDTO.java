package com.personaltrainer.gymmanager.model.dtos;

import java.util.Set;

import com.personaltrainer.gymmanager.model.entidades.Aluno;
import com.personaltrainer.gymmanager.model.enums.GrupoMuscular;
import com.personaltrainer.gymmanager.model.enums.TipoEstimulo;
import com.personaltrainer.gymmanager.model.enums.TipoTreino;

public record TreinoResponseDTO(
    Long id,
    Aluno aluno,
    TipoTreino tipoTreino,
    Set<GrupoMuscular> gruposMusculares,
    int quantidadeSeries,
    int quantidadeRepeticoes,
    double carga,
    TipoEstimulo tipoEstimulo
) {
    
}
