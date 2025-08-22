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

import com.personaltrainer.gymmanager.model.dtos.TreinoRequestDTO;
import com.personaltrainer.gymmanager.model.dtos.TreinoResponseDTO;
import com.personaltrainer.gymmanager.service.TreinoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/treinos")
public class TreinoController {
    @Autowired
    private TreinoService treinoService;

    @GetMapping
    public List<TreinoResponseDTO> listarTreinos() {
        return treinoService.listarTreinos();
    }

    @GetMapping("/{id}")
    public TreinoResponseDTO buscarTreino(@PathVariable Long id) {
        return treinoService.buscarTreino(id);
    }

    @PostMapping
    public TreinoResponseDTO criarTreino(@RequestBody TreinoRequestDTO treino) {
        return treinoService.criarTreino(treino);
    }

    @PutMapping("/{id}")
    public TreinoResponseDTO atualizarTreino(@PathVariable Long id, @RequestBody TreinoRequestDTO treino) {
        return treinoService.atualizarTreino(id, treino);
    }

    @DeleteMapping("/{id}")
    public void deletarTreino(@PathVariable Long id) {
        treinoService.deletarTreino(id);
    }
}
