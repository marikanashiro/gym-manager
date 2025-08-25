package com.personaltrainer.gymmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personaltrainer.gymmanager.model.dtos.TipoTreinoRequestDTO;
import com.personaltrainer.gymmanager.model.dtos.TipoTreinoResponseDTO;
import com.personaltrainer.gymmanager.model.dtos.TreinoRequestDTO;
import com.personaltrainer.gymmanager.model.dtos.TreinoResponseDTO;
import com.personaltrainer.gymmanager.model.entidades.TipoTreino;
import com.personaltrainer.gymmanager.model.entidades.Treino;
import com.personaltrainer.gymmanager.repository.TreinoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    private TreinoResponseDTO entityToDTO(Treino treino) {
        return new TreinoResponseDTO(
                treino.getId(),
                treino.getAluno() != null ? treino.getAluno().getId() : null,
                treino.getDataCriacao(),
                treino.getDataExpiracao(),
                treino.getTipoEstimulo(),
                treino.getTipoTreinos().stream().map(this::tipoTreinoToDTO).collect(Collectors.toList()));
    }

    private TipoTreinoResponseDTO tipoTreinoToDTO(TipoTreino tipoTreino) {
        return new TipoTreinoResponseDTO(
                tipoTreino.getId(),
                tipoTreino.getTreino() != null ? tipoTreino.getTreino().getId() : null,
                tipoTreino.getGruposMusculares(),
                tipoTreino.getQuantidadeSeries(),
                tipoTreino.getQuantidadeRepeticoes(),
                tipoTreino.getExercicios());
    }

    private Treino dtoToEntity(TreinoRequestDTO treinoRequestDTO) {
        Treino treino = new Treino();
        treino.setAluno(treinoRequestDTO.aluno());
        treino.setDataCriacao(treinoRequestDTO.dataCriacao());
        treino.setDataExpiracao(treinoRequestDTO.dataExpiracao());
        treino.setTipoEstimulo(treinoRequestDTO.tipoEstimulo());

        List<TipoTreino> tipoTreinos = new ArrayList<>();
        if (treinoRequestDTO.tipoTreinos() != null) {
            for (TipoTreinoRequestDTO tipoDTO : treinoRequestDTO.tipoTreinos()) {
                TipoTreino tipoTreino = dtoToTipoTreinoEntity(tipoDTO);
                tipoTreino.setTreino(treino);
                tipoTreinos.add(tipoTreino);
            }
        }
        treino.setTipoTreinos(tipoTreinos);
        return treino;
    }

    private TipoTreino dtoToTipoTreinoEntity(TipoTreinoRequestDTO tipoTreinoRequestDTO) {
        TipoTreino tipoTreino = new TipoTreino();
        tipoTreino.setTreino(tipoTreinoRequestDTO.treino());
        tipoTreino.setGruposMusculares(tipoTreinoRequestDTO.gruposMusculares());
        tipoTreino.setQuantidadeSeries(tipoTreinoRequestDTO.quantidadeSeries());
        tipoTreino.setQuantidadeRepeticoes(tipoTreinoRequestDTO.quantidadeRepeticoes());
        tipoTreino.setExercicios(tipoTreinoRequestDTO.exercicios());
        return tipoTreino;
    }

    public List<TreinoResponseDTO> listarTreinos() {
        List<TreinoResponseDTO> treinos = treinoRepository.findAll()
                .stream().map(this::entityToDTO)
                .collect(Collectors.toList());
        return treinos;
    }

    public TreinoResponseDTO criarTreino(TreinoRequestDTO treinoRequestDTO) {
        Treino treinoEntity = dtoToEntity(treinoRequestDTO);
        Treino treinoSalvo = treinoRepository.save(treinoEntity);
        return entityToDTO(treinoSalvo);
    }

    public TreinoResponseDTO buscarTreino(Long id) {
        Optional<Treino> treino = treinoRepository.findById(id);
        return treino.map(this::entityToDTO).orElseThrow(() -> new RuntimeException("Treino não encontrado"));
    }

    public TreinoResponseDTO atualizarTreino(Long id, TreinoRequestDTO dto) {
        // verificando se o treino existe
        Optional<Treino> treino = treinoRepository.findById(id);

        // se existir, atualiza os dados
        if (treino.isPresent()) {
            Treino treinoAtualizado = treino.get();
            treinoAtualizado.setAluno(dto.aluno());
            treinoAtualizado.setTipoEstimulo(dto.tipoEstimulo());
            treinoAtualizado.setDataCriacao(dto.dataCriacao());
            treinoAtualizado.setDataExpiracao(dto.dataExpiracao());
            if (dto.tipoTreinos() != null) {
                List<TipoTreino> tipoTreinos = dto.tipoTreinos()
                        .stream().map(this::dtoToTipoTreinoEntity)
                        .collect(Collectors.toList());
                treinoAtualizado.setTipoTreinos(tipoTreinos);
            }
            treinoAtualizado.setTipoEstimulo(dto.tipoEstimulo());

            // salvando as alterações
            Treino treinoSalvo = treinoRepository.save(treinoAtualizado);
            return entityToDTO(treinoSalvo);
        }

        // se o treino não existir
        throw new EntityNotFoundException("Treino com ID " + id + " não encontrado.");
    }

    public void deletarTreino(Long id) {
        if (!treinoRepository.existsById(id)) {
            throw new RuntimeException("Treino não encontrado.");
        }
        treinoRepository.deleteById(id);
    }
}
