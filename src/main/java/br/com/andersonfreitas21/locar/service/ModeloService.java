package br.com.andersonfreitas21.locar.service;

import br.com.andersonfreitas21.locar.controller.PagedResult;
import br.com.andersonfreitas21.locar.controller.modelos.dtos.FindModelosQuery;
import br.com.andersonfreitas21.locar.controller.modelos.dtos.ModeloDTO;
import br.com.andersonfreitas21.locar.controller.modelos.dtos.ModeloRequest;

public interface ModeloService {
    PagedResult<ModeloDTO> findModelos(FindModelosQuery query);

    ModeloDTO findById(Integer id);

    ModeloDTO create(ModeloRequest modeloRequest);

    void update(Integer id, String nome);

    void delete(Integer id);
}
