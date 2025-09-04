package com.personaltrainer.gymmanager.model.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Treino> treinos = new ArrayList<>();
}
