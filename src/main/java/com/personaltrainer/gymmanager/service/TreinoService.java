package com.personaltrainer.gymmanager.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personaltrainer.gymmanager.model.dtos.TreinoRequestDTO;
import com.personaltrainer.gymmanager.model.dtos.TreinoResponseDTO;
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
            treino.getAluno(),
            treino.getTipoTreino(),
            treino.getGruposMusculares(),
            treino.getQuantidadeSeries(),
            treino.getQuantidadeRepeticoes(),
            treino.getCarga(),
            treino.getTipoEstimulo());
    }

    private Treino dtoToEntity(TreinoRequestDTO treinoRequestDTO) {
        Treino treino = new Treino();
        treino.setAluno(treinoRequestDTO.aluno());
        treino.setTipoTreino(treinoRequestDTO.tipoTreino());
        treino.setGruposMusculares(treinoRequestDTO.gruposMusculares());
        treino.setQuantidadeSeries(treinoRequestDTO.quantidadeSeries());
        treino.setQuantidadeRepeticoes(treinoRequestDTO.quantidadeRepeticoes());
        treino.setCarga(treinoRequestDTO.carga());
        treino.setTipoEstimulo(treinoRequestDTO.tipoEstimulo());
        return treino;
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
            treinoAtualizado.setTipoTreino(dto.tipoTreino());
            treinoAtualizado.setGruposMusculares(dto.gruposMusculares());
            treinoAtualizado.setQuantidadeSeries(dto.quantidadeSeries());
            treinoAtualizado.setQuantidadeRepeticoes(dto.quantidadeRepeticoes());
            treinoAtualizado.setCarga(dto.carga());
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
            throw new RuntimeException("Treino não encontrado");
        }
        treinoRepository.deleteById(id);
    }
}
