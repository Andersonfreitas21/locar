package br.com.andersonfreitas21.locar.controller.dto;

import jakarta.validation.constraints.NotEmpty;

public record UpdateMarcaRequest(
        @NotEmpty(message = "Nome é obrigatório")
        String nome
) {
}
