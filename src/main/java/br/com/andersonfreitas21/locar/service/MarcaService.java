package br.com.andersonfreitas21.locar.service;

import br.com.andersonfreitas21.locar.controller.marcas.dtos.FindMarcasQuery;
import br.com.andersonfreitas21.locar.controller.marcas.dtos.MarcaDTO;
import br.com.andersonfreitas21.locar.controller.marcas.dtos.MarcaRequest;
import br.com.andersonfreitas21.locar.controller.PagedResult;
import jakarta.persistence.EntityNotFoundException;

public interface MarcaService {
    PagedResult<MarcaDTO> findMarcas(FindMarcasQuery query);

    MarcaDTO findById(Integer id) throws EntityNotFoundException;

    MarcaDTO create(MarcaRequest marcaRequest);

    void update(Integer id, String nome);

    void delete(Integer id);
}
