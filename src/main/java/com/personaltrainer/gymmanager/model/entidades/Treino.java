package com.personaltrainer.gymmanager.model.entidades;

import java.util.Set;

import com.personaltrainer.gymmanager.model.enums.GrupoMuscular;
import com.personaltrainer.gymmanager.model.enums.TipoEstimulo;
import com.personaltrainer.gymmanager.model.enums.TipoTreino;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Treino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Aluno aluno;
    @ElementCollection(targetClass = TipoTreino.class)
    @Enumerated(EnumType.STRING)
    private TipoTreino tipoTreino;
    @ElementCollection(targetClass = GrupoMuscular.class)
    @Enumerated(EnumType.STRING)
    private Set<GrupoMuscular> gruposMusculares;
    private int quantidadeSeries;
    private int quantidadeRepeticoes;
    private double carga;
    @Enumerated(EnumType.STRING)
    private TipoEstimulo tipoEstimulo;
}
