package br.com.andersonfreitas21.locar.controller.marcas.dtos;

import jakarta.validation.constraints.NotEmpty;

public record MarcaRequest(
        @NotEmpty(message = "Nome é obrigatório")
        String nome) {
}
