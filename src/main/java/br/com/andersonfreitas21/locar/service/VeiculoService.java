package br.com.andersonfreitas21.locar.service;

import br.com.andersonfreitas21.locar.controller.FindEntityQuery;
import br.com.andersonfreitas21.locar.controller.PagedResult;
import br.com.andersonfreitas21.locar.controller.veiculos.dtos.VeiculoDTO;
import br.com.andersonfreitas21.locar.controller.veiculos.dtos.VeiculoRequest;

public interface VeiculoService {
    PagedResult<VeiculoDTO> findVeiculos(FindEntityQuery query);

    VeiculoDTO findById(Integer id);

    VeiculoDTO create(VeiculoRequest request);

    void delete(Integer id);

    void update(Integer id, VeiculoRequest request);
}
