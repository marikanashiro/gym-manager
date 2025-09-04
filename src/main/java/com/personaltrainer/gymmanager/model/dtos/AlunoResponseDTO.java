package com.personaltrainer.gymmanager.model.dtos;

import java.time.LocalDate;
import java.util.List;

public record AlunoResponseDTO(
        Long id,
        String nome,
        String telefone,
        String email,
        String endereco,
        LocalDate dataNascimento,
        List<TreinoResponseDTO> treinos) {

}
