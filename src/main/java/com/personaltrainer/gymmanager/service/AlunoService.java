package com.personaltrainer.gymmanager.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personaltrainer.gymmanager.model.dtos.AlunoRequestDTO;
import com.personaltrainer.gymmanager.model.dtos.AlunoResponseDTO;
import com.personaltrainer.gymmanager.model.dtos.TipoTreinoResponseDTO;
import com.personaltrainer.gymmanager.model.dtos.TreinoResponseDTO;
import com.personaltrainer.gymmanager.model.entidades.Aluno;
import com.personaltrainer.gymmanager.repository.AlunoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    private AlunoResponseDTO entityToDTO(Aluno aluno) {
        List<TreinoResponseDTO> treinosDTO = aluno.getTreinos().stream().map(treino -> new TreinoResponseDTO(
            treino.getId(),
            treino.getAluno().getId(),
            treino.getDataCriacao(),
            treino.getDataExpiracao(),
            treino.getTipoEstimulo(),
            treino.getTipoTreinos().stream().map(tipoTreino -> new TipoTreinoResponseDTO(
                tipoTreino.getId(),
                tipoTreino.getTreino().getId(),
                tipoTreino.getGruposMusculares(),
                tipoTreino.getQuantidadeSeries(),
                tipoTreino.getQuantidadeRepeticoes(),
                tipoTreino.getExercicios()
            )).collect(Collectors.toList())
        )).collect(Collectors.toList());

        return new AlunoResponseDTO(
            aluno.getId(), 
            aluno.getNome(), 
            aluno.getTelefone(), 
            aluno.getEmail(), 
            aluno.getEndereco(), 
            aluno.getDataNascimento(),
            treinosDTO);
    }

    private Aluno dtoToEntity(AlunoRequestDTO alunoRequestDTO) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoRequestDTO.nome());
        aluno.setTelefone(alunoRequestDTO.telefone());
        aluno.setEmail(alunoRequestDTO.email());
        aluno.setEndereco(alunoRequestDTO.endereco());
        aluno.setDataNascimento(alunoRequestDTO.dataNascimento());
        return aluno;
    }

    public List<AlunoResponseDTO> listarAlunos() {
        List<AlunoResponseDTO> alunos = alunoRepository.findAll()
            .stream()
            .map(this::entityToDTO)
            .collect(Collectors.toList());
        return alunos;
    }

    public AlunoResponseDTO buscarAluno(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        
        return aluno.map(this::entityToDTO)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public AlunoResponseDTO criarAluno(AlunoRequestDTO alunoRequestDTO) {
        Aluno alunoEntity = dtoToEntity(alunoRequestDTO);
        Aluno alunoSalvo = alunoRepository.save(alunoEntity);
        return entityToDTO(alunoSalvo);
    }

    public AlunoResponseDTO atualizaAluno(Long id, AlunoRequestDTO dto) {
        // verificando se o aluno existe
        Optional<Aluno> aluno = alunoRepository.findById(id);

        // se existir, atualiza os dados
        if (aluno.isPresent()) {
            Aluno alunoAtualizado = aluno.get();
            alunoAtualizado.setNome(dto.nome());
            alunoAtualizado.setTelefone(dto.telefone());
            alunoAtualizado.setEmail(dto.email());
            alunoAtualizado.setEndereco(dto.endereco());
            alunoAtualizado.setDataNascimento(dto.dataNascimento());

            //salvando alterações
            Aluno alunoSalvo = alunoRepository.save(alunoAtualizado);
            return entityToDTO(alunoSalvo);
        }
        
        // se o aluno não existir
        throw new EntityNotFoundException("Aluno com ID " + id + " não encontrado.");
    }

    public void deletarAluno(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new RuntimeException("Aluno não encontrado");
        }
        alunoRepository.deleteById(id);
    }
}
