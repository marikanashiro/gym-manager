package com.personaltrainer.gymmanager.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personaltrainer.gymmanager.model.dtos.TipoTreinoRequestDTO;
import com.personaltrainer.gymmanager.model.dtos.TipoTreinoResponseDTO;
import com.personaltrainer.gymmanager.model.entidades.TipoTreino;
import com.personaltrainer.gymmanager.repository.TipoTreinoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TipoTreinoService {
    
    @Autowired
    private TipoTreinoRepository tipoTreinoRepository;

    private TipoTreinoResponseDTO entityToDTO(TipoTreino tipoTreino) {
        return new TipoTreinoResponseDTO(
            tipoTreino.getId(), 
            tipoTreino.getTreino(), 
            tipoTreino.getGruposMusculares(), 
            tipoTreino.getQuantidadeSeries(),
            tipoTreino.getQuantidadeRepeticoes(), 
            tipoTreino.getExercicios());
    }

    private TipoTreino dtoToEntity(TipoTreinoRequestDTO tipoTreinoRequestDTO) {
        TipoTreino tipoTreino = new TipoTreino();
        tipoTreino.setTreino(tipoTreinoRequestDTO.treino());
        tipoTreino.setGruposMusculares(tipoTreinoRequestDTO.gruposMusculares());
        tipoTreino.setQuantidadeSeries(tipoTreinoRequestDTO.quantidadeSeries());
        tipoTreino.setQuantidadeRepeticoes(tipoTreinoRequestDTO.quantidadeRepeticoes());
        tipoTreino.setExercicios(tipoTreinoRequestDTO.exercicios());
        return tipoTreino;
    }

    public List<TipoTreinoResponseDTO> listarTipoTreinos() {
        List<TipoTreinoResponseDTO> tipoTreinos = tipoTreinoRepository.findAll()
            .stream().map(this::entityToDTO)
            .collect(Collectors.toList());
        return tipoTreinos;
    }

    public TipoTreinoResponseDTO criarTipoTreino(TipoTreinoRequestDTO tipoTreinoRequestDTO) {
        TipoTreino tipoTreinoEntity = dtoToEntity(tipoTreinoRequestDTO);
        TipoTreino tipoTreinoSalvo = tipoTreinoRepository.save(tipoTreinoEntity);
        return entityToDTO(tipoTreinoSalvo);
    }

    public TipoTreinoResponseDTO buscarTipoTreino(Long id) {
        Optional<TipoTreino> tipoTreino = tipoTreinoRepository.findById(id);
        return tipoTreino.map(this::entityToDTO).orElseThrow(() -> new RuntimeException("Tipo de treino não encontrado"));
    }

    public TipoTreinoResponseDTO atualizarTipoTreino(Long id, TipoTreinoRequestDTO dto) {
        // verificando se o tipo treino existe
        Optional<TipoTreino> tipoTreino = tipoTreinoRepository.findById(id);

        // se existir, atualiza os dados
        if (tipoTreino.isPresent()) {
            TipoTreino tipoTreinoAtualizado = tipoTreino.get();
            tipoTreinoAtualizado.setTreino(dto.treino());
            tipoTreinoAtualizado.setGruposMusculares(dto.gruposMusculares());
            tipoTreinoAtualizado.setQuantidadeSeries(dto.quantidadeSeries());
            tipoTreinoAtualizado.setQuantidadeRepeticoes(dto.quantidadeRepeticoes());
            tipoTreinoAtualizado.setExercicios(dto.exercicios());
            
            //salvando as alterações
            TipoTreino tipoTreinoSalvo = tipoTreinoRepository.save(tipoTreinoAtualizado);
            return entityToDTO(tipoTreinoSalvo);
        }

        //se o treino não existir
        throw new EntityNotFoundException("Tipo treino com ID: " + id + " não encontrado.");
    }

    public void deletarTipoTreino(Long id) {
        if (!tipoTreinoRepository.existsById(id)) {
            throw new RuntimeException("Tipo treino não encontrado.");
        }
        tipoTreinoRepository.deleteById(id);
    }
}
