package com.personaltrainer.gymmanager.model.entidades;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.personaltrainer.gymmanager.model.enums.GrupoMuscular;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TipoTreino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "treino_id")
    @JsonBackReference
    private Treino treino;
    @ElementCollection(targetClass = GrupoMuscular.class)
    @Enumerated(EnumType.STRING)
    private Set<GrupoMuscular> gruposMusculares;
    private int quantidadeSeries;
    private int quantidadeRepeticoes;
    private String exercicios;
}