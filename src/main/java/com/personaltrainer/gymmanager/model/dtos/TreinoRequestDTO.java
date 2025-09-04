package com.personaltrainer.gymmanager.model.dtos;

import java.time.LocalDate;
import java.util.List;

import com.personaltrainer.gymmanager.model.enums.TipoEstimulo;

public record TreinoRequestDTO(
    Long alunoId,
    LocalDate dataCriacao,
    LocalDate dataExpiracao,
    List<TipoTreinoRequestDTO> tipoTreinos,
    TipoEstimulo tipoEstimulo
) {
    
}
