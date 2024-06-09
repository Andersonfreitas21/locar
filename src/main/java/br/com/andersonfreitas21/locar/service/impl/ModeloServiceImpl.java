package br.com.andersonfreitas21.locar.service.impl;

import br.com.andersonfreitas21.locar.controller.PagedResult;
import br.com.andersonfreitas21.locar.controller.modelos.dtos.FindModelosQuery;
import br.com.andersonfreitas21.locar.controller.modelos.dtos.ModeloDTO;
import br.com.andersonfreitas21.locar.controller.modelos.dtos.ModeloRequest;
import br.com.andersonfreitas21.locar.exception.EntityException;
import br.com.andersonfreitas21.locar.exception.EntityNotFoundException;
import br.com.andersonfreitas21.locar.model.modelo.ModeloEntity;
import br.com.andersonfreitas21.locar.repository.ModeloRepository;
import br.com.andersonfreitas21.locar.service.ModeloService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModeloServiceImpl implements ModeloService {
    private final ModeloRepository repository;

    @Override
    @Transactional(readOnly = true)
    public PagedResult<ModeloDTO> findModelos(FindModelosQuery query) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        int pageNo = query.pageNo() > 0 ? query.pageNo() - 1 : 0;
        Pageable pageable = PageRequest.of(pageNo, query.pageSize(), sort);
        Page<ModeloDTO> page = repository.findModelos(pageable);
        return new PagedResult<>(
                page.getContent(),
                page.getTotalElements(),
                page.getNumber() + 1, // for user page number starts from 1
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ModeloDTO findById(Integer id) {
        return repository.findModeloById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    @Transactional
    public ModeloDTO create(ModeloRequest modeloRequest) {
        var nomeModelo = modeloRequest.nome();
        Optional<ModeloEntity> existingModelo = repository.findByNome(nomeModelo);
        if (existingModelo.isPresent()) {
            throw new EntityException(nomeModelo);
        }

        ModeloEntity modeloEntity = new ModeloEntity(nomeModelo, Instant.now());

        return ModeloDTO.fromEntity(repository.save(modeloEntity));
    }

    @Override
    @Transactional
    public void update(Integer id, String nome) {
        ModeloEntity modeloEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        modeloEntity.setNome(nome);
        repository.save(modeloEntity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        ModeloEntity modeloEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        repository.delete(modeloEntity);
    }

    @Override
    public List<ModeloDTO> findByMarca(Integer idMarca) {
        return repository.findModeloByIdMarca(idMarca);
    }
}
