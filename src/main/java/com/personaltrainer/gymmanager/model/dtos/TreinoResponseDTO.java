package com.personaltrainer.gymmanager.model.dtos;

import java.time.LocalDate;
import java.util.List;

import com.personaltrainer.gymmanager.model.enums.TipoEstimulo;

public record TreinoResponseDTO(
    Long id,
    Long alunoId,
    LocalDate dataCriacao,
    LocalDate dataExpiracao,
    TipoEstimulo tipoEstimulo,
    List<TipoTreinoResponseDTO> tipoTreinos
) {
    
}
