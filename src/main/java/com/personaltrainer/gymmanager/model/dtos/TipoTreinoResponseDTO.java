package com.personaltrainer.gymmanager.model.dtos;

import java.util.Set;

import com.personaltrainer.gymmanager.model.entidades.Treino;
import com.personaltrainer.gymmanager.model.enums.GrupoMuscular;

public record TipoTreinoResponseDTO(
    Long id,
    Treino treino,
    Set<GrupoMuscular> gruposMusculares,
    int quantidadeSeries,
    int quantidadeRepeticoes,
    String exercicios
) {
    
}
