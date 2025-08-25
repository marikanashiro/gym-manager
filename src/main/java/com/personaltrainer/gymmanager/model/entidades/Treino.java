package com.personaltrainer.gymmanager.model.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.personaltrainer.gymmanager.model.enums.TipoEstimulo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
    @Enumerated(EnumType.STRING)
    private TipoEstimulo tipoEstimulo;
    private LocalDate dataCriacao;
    private LocalDate dataExpiracao;
    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TipoTreino> tipoTreinos = new ArrayList<>();
}
