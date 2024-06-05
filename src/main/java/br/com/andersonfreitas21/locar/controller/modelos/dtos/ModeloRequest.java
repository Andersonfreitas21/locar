package br.com.andersonfreitas21.locar.controller.modelos.dtos;

import jakarta.validation.constraints.NotEmpty;

public record ModeloRequest(@NotEmpty(message = "Nome é obrigatório")
                            String nome) {
}
