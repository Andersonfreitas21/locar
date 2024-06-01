package br.com.andersonfreitas21.locar.service;

import br.com.andersonfreitas21.locar.controller.dto.FindMarcasQuery;
import br.com.andersonfreitas21.locar.controller.dto.MarcaDTO;
import br.com.andersonfreitas21.locar.controller.dto.MarcaRequest;
import br.com.andersonfreitas21.locar.controller.dto.PagedResult;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public interface MarcaService {
    PagedResult<MarcaDTO> findMarcas(FindMarcasQuery query);

    Optional<MarcaDTO> findById(Integer id) throws EntityNotFoundException;

    MarcaDTO create(MarcaRequest marcaRequest);

    void update(Integer id, String nome);

    void delete(Integer id);
}
