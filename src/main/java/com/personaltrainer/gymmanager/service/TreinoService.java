package com.personaltrainer.gymmanager.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personaltrainer.gymmanager.model.entidades.Treino;
import com.personaltrainer.gymmanager.repository.TreinoRepository;

@Service
public class TreinoService {
    
    @Autowired
    private TreinoRepository treinoRepository;

    public List<Treino> listarTreinos() {
        return treinoRepository.findAll();
    }

    public Treino criarTreino(Treino treino) {
        // logica
        return treinoRepository.save(treino);
    }

    public Treino buscarTreino(Long id) {
        return treinoRepository.findById(id).orElseThrow(() -> new RuntimeException("Treino não encontrado"));
    }

    public Treino atualizarTreino(Long id, Treino treino) {
        treino.setId(id);
        //logica
        return treinoRepository.save(treino);
    }

    public void deletarTreino(Long id) {
        if (!treinoRepository.existsById(id)) {
            throw new RuntimeException("Treino não encontrado");
        }
        treinoRepository.deleteById(id);
    }
}
