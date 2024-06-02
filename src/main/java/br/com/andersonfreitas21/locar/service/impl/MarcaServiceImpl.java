package br.com.andersonfreitas21.locar.service.impl;

import br.com.andersonfreitas21.locar.controller.dto.FindMarcasQuery;
import br.com.andersonfreitas21.locar.controller.dto.MarcaDTO;
import br.com.andersonfreitas21.locar.controller.dto.MarcaRequest;
import br.com.andersonfreitas21.locar.controller.dto.PagedResult;
import br.com.andersonfreitas21.locar.exception.EntityMarcaException;
import br.com.andersonfreitas21.locar.exception.MarcaNotFoundException;
import br.com.andersonfreitas21.locar.model.Marca;
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
    public PagedResult<MarcaDTO> findMarcas(FindMarcasQuery query) {
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
                .orElseThrow(() -> new MarcaNotFoundException(id));
    }

    @Override
    @Transactional
    public MarcaDTO create(MarcaRequest marcaRequest) {
        String nomeMarca = marcaRequest.nome();
        Optional<Marca> existingMarca = repository.findByNome(nomeMarca);

        if (existingMarca.isPresent()) {
            throw new EntityMarcaException(nomeMarca);
        }
        Marca marca = new Marca(nomeMarca, Instant.now());
        return MarcaDTO.fromEntity(repository.save(marca));
    }

    @Override
    @Transactional
    public void update(Integer id, String nome) {
        Marca marca = repository.findById(id)
                .orElseThrow(() -> new MarcaNotFoundException(id));
        marca.setNome(nome);
        repository.save(marca);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Marca marca = repository.findById(id)
                .orElseThrow(() -> new MarcaNotFoundException(id));
        repository.delete(marca);
    }
}
