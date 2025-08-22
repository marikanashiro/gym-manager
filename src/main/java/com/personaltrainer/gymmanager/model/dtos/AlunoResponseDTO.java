package com.personaltrainer.gymmanager.model.dtos;

import java.time.LocalDate;

public record AlunoResponseDTO(
    Long id,
    String nome,
    String telefone,
    String email,
    String endereco,
    LocalDate dataNascimento
) {
    
}
