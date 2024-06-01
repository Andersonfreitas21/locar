package br.com.andersonfreitas21.locar.controller.dto;

import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;

public record MarcaRequest(
        @NotEmpty(message = "Nome é obrigatório")
        String nome) {
}
