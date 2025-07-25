package com.personaltrainer.gymmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.personaltrainer.gymmanager.model.entidades.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
