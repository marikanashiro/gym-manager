package com.personaltrainer.gymmanager.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoEstimulo {
    FORCA("Força"), 
    METABOLICO("Metabólico"), 
    HIPERTROFIA("Hipertrofia"), 
    RESISTENCIA("Resistência"), 
    ESPECIFICO("Específico");

    private final String nome;

    TipoEstimulo(String nome) {
        this.nome = nome;
    }

    @JsonValue
    public String getNome() {
        return nome;
    }

    @JsonCreator
    public static TipoEstimulo fromValue(String value) {
        for (TipoEstimulo tipo : TipoEstimulo.values()) {
            if (tipo.nome.equals(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor inválido para TipoEstimulo: " + value);
    }
}
