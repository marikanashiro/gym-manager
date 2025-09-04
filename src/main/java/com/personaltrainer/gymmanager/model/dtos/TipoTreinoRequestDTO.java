package com.personaltrainer.gymmanager.model.dtos;

import java.util.Set;

import com.personaltrainer.gymmanager.model.enums.GrupoMuscular;

public record TipoTreinoRequestDTO(
    Long treinoId,
    Set<GrupoMuscular> gruposMusculares,
    int quantidadeSeries,
    int quantidadeRepeticoes,
    String exercicios
) {
    
}
