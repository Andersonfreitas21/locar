package br.com.andersonfreitas21.locar.service.impl;

import br.com.andersonfreitas21.locar.controller.FindEntityQuery;
import br.com.andersonfreitas21.locar.controller.marcas.dtos.MarcaDTO;
import br.com.andersonfreitas21.locar.controller.marcas.dtos.MarcaRequest;
import br.com.andersonfreitas21.locar.controller.PagedResult;
import br.com.andersonfreitas21.locar.exception.EntityException;
import br.com.andersonfreitas21.locar.exception.EntityNotFoundException;
import br.com.andersonfreitas21.locar.model.marca.MarcaEntity;
import br.com.andersonfreitas21.locar.repository.MarcaRepository;
import br.com.andersonfreitas21.locar.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarcaServiceImpl implements MarcaService {
    private final MarcaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public PagedResult<MarcaDTO> findMarcas(FindEntityQuery query) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        int pageNo = query.pageNo() > 0 ? query.pageNo() - 1 : 0;
        Pageable pageable = PageRequest.of(pageNo, query.pageSize(), sort);
        Page<MarcaDTO> page = repository.findMarcas(pageable);
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
    public MarcaDTO findById(Integer id) {
        return repository.findMarcaById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    @Transactional
    public MarcaDTO create(MarcaRequest marcaRequest) {
        String nomeMarca = marcaRequest.nome();
        Optional<MarcaEntity> existingMarca = repository.findByNome(nomeMarca);

        if (existingMarca.isPresent()) {
            throw new EntityException(nomeMarca);
        }
        MarcaEntity marcaEntity = new MarcaEntity(nomeMarca, Instant.now());
        return MarcaDTO.fromEntity(repository.save(marcaEntity));
    }

    @Override
    @Transactional
    public void update(Integer id, String nome) {
        MarcaEntity marcaEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        marcaEntity.setNome(nome);
        repository.save(marcaEntity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        MarcaEntity marcaEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        repository.delete(marcaEntity);
    }
}
