package br.com.andersonfreitas21.locar.controller.veiculos.dtos;

public record VeiculoRequestCriteria(
        int pageNo,
        int pageSize,
        String chassi,
        String placa,
        String anoFabricacao,
        Integer kmAtual,
        String idMarca,
        String idModelo) {
}
