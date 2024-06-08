package br.com.andersonfreitas21.locar.controller.veiculos.dtos;

import br.com.andersonfreitas21.locar.model.veiculo.VeiculoEntity;

public record VeiculoDTO(Integer id, String chassi, String placa, String descricao, Integer idMarca, Integer idModelo) {
    public static VeiculoDTO fromEntity(VeiculoEntity veiculo) {
        return new VeiculoDTO(veiculo.getId(), veiculo.getChassi(), veiculo.getPlaca(), veiculo.getDescricao(), veiculo.getIdMarca(), veiculo.getIdModelo());
    }
}
