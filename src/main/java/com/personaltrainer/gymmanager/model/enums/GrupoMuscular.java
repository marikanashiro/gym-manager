package com.personaltrainer.gymmanager.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GrupoMuscular {
    TRAPEZIO("Trapézio"), 
    DELTOIDE("Deltoide"), 
    PEITORAL("Peitoral"), 
    DORSAL("Dorsal"), 
    ISQUIOTIBIAL("Isquiotibial"), 
    QUADRICEPS("Quadríceps"), 
    GLUTEO("Glúteo"), 
    BICEPS("Bíceps"), 
    TRICEPS("Tríceps"), 
    ANTEBRACO("Antebraço"), 
    PANTURRILHA("Panturrilha"), 
    ABDOMINAL("Abdominal");

    private final String nome;

    GrupoMuscular(String nome) {
        this.nome = nome;
    }

    @JsonValue
    public String getNome() {
        return nome;
    }

    @JsonCreator
    public static GrupoMuscular fromValue(String value) {
        for (GrupoMuscular grupo : GrupoMuscular.values()) {
            if (grupo.nome.equals(value)) {
                return grupo;
            }
        }
        throw new IllegalArgumentException("Valor inválido para GrupoMuscular: " + value);
    }
}
