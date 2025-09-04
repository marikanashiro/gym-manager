package com.personaltrainer.gymmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.personaltrainer.gymmanager.model.entidades.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @EntityGraph(attributePaths = {"treinos"})
    List<Aluno> findAll();
}
