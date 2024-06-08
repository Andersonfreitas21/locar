package br.com.andersonfreitas21.locar.controller.veiculos.dtos;

import jakarta.validation.constraints.NotNull;

public record VeiculoRequest(
        @NotNull
        String chassi,
        @NotNull
        String placa,
        @NotNull
        Integer idMarca,
        @NotNull
        Integer idModelo,
        String descricao,
        String anoFabricacao,
        Integer kmAtual) {
}
