package com.personaltrainer.gymmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personaltrainer.gymmanager.model.entidades.Treino;

public interface TreinoRepository extends JpaRepository<Treino, Long> {

}
