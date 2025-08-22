package com.personaltrainer.gymmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personaltrainer.gymmanager.model.dtos.AlunoRequestDTO;
import com.personaltrainer.gymmanager.model.dtos.AlunoResponseDTO;
import com.personaltrainer.gymmanager.service.AlunoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<AlunoResponseDTO> listarAlunos() {
        return alunoService.listarAlunos();
    }

    @GetMapping("/{id}")
    public AlunoResponseDTO buscarAluno(@PathVariable Long id) {
        return alunoService.buscarAluno(id);
    }

    @PostMapping
    public AlunoResponseDTO criarAluno(@RequestBody AlunoRequestDTO aluno) {
        return alunoService.criarAluno(aluno);
    }

    @PutMapping("/{id}")
    public AlunoResponseDTO atualizarAluno(@PathVariable Long id, @RequestBody AlunoRequestDTO aluno) {
        return alunoService.atualizaAluno(id, aluno);
    }

    @DeleteMapping("/{id}")
    public void deletarAluno(@PathVariable Long id) {
        alunoService.deletarAluno(id);
    }
}
