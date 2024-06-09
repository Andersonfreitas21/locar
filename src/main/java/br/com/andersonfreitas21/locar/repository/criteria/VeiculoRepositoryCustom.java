package br.com.andersonfreitas21.locar.repository.criteria;

import br.com.andersonfreitas21.locar.controller.veiculos.dtos.VeiculoRequestCriteria;
import br.com.andersonfreitas21.locar.model.veiculo.VeiculoEntity;

import java.util.List;

public interface VeiculoRepositoryCustom {
    List<VeiculoEntity> findVeiculosFilter(VeiculoRequestCriteria criteria);
}
