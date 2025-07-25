package com.personaltrainer.gymmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.personaltrainer.gymmanager.model.entidades.Aluno;
import com.personaltrainer.gymmanager.repository.AlunoRepository;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarAluno(Long id) {
        return alunoRepository.findById(id)
        .orElseThrow(() -> 
        new RuntimeException("Aluno não encontrado"));
    }

    public Aluno criarAluno(Aluno aluno) {
        //logica
        return alunoRepository.save(aluno);
    }

    public Aluno atualizaAluno(Long id, Aluno aluno) {
        //logica
        return alunoRepository.save(aluno);
    }

    public void deletarAluno(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new RuntimeException("Aluno não encontrado");
        }
        alunoRepository.deleteById(id);
    }
}
