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

import com.personaltrainer.gymmanager.model.dtos.TipoTreinoRequestDTO;
import com.personaltrainer.gymmanager.model.dtos.TipoTreinoResponseDTO;
import com.personaltrainer.gymmanager.service.TipoTreinoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/tipoTreinos")
public class TipoTreinoController {
    
    @Autowired
    private TipoTreinoService tipoTreinoService;

    @GetMapping
    public List<TipoTreinoResponseDTO> listarTipoTreinos() {
        return tipoTreinoService.listarTipoTreinos();
    }

    @GetMapping("/{id}")
    public TipoTreinoResponseDTO buscarTipoTreino(@PathVariable Long id) {
        return tipoTreinoService.buscarTipoTreino(id);
    }

    @PostMapping
    public TipoTreinoResponseDTO criarTipoTreino(@RequestBody TipoTreinoRequestDTO tipoTreino) {
        return tipoTreinoService.criarTipoTreino(tipoTreino);
    }

    @PutMapping("/{id}")
    public TipoTreinoResponseDTO atualizarTipoTreino(@PathVariable Long id, @RequestBody TipoTreinoRequestDTO tipoTreino) {
        return tipoTreinoService.atualizarTipoTreino(id, tipoTreino);
    }

    @DeleteMapping("/{id}")
    public void deletarTipoTreino(@PathVariable Long id) {
        tipoTreinoService.deletarTipoTreino(id);
    }
}
