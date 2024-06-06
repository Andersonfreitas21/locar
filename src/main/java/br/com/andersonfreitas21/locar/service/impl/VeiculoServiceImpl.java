package br.com.andersonfreitas21.locar.service.impl;

import br.com.andersonfreitas21.locar.controller.FindEntityQuery;
import br.com.andersonfreitas21.locar.controller.PagedResult;
import br.com.andersonfreitas21.locar.controller.veiculos.dtos.VeiculoDTO;
import br.com.andersonfreitas21.locar.controller.veiculos.dtos.VeiculoRequest;
import br.com.andersonfreitas21.locar.service.VeiculoService;
import org.springframework.stereotype.Service;

@Service
public class VeiculoServiceImpl implements VeiculoService {
    @Override
    public PagedResult<VeiculoDTO> findVeiculos(FindEntityQuery query) {
        return null;
    }

    @Override
    public VeiculoDTO findById(Integer id) {
        return null;
    }

    @Override
    public VeiculoDTO create(VeiculoRequest request) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Integer id, VeiculoRequest request) {

    }
}
