package br.com.andersonfreitas21.locar.service.impl;

import br.com.andersonfreitas21.locar.controller.FindEntityQuery;
import br.com.andersonfreitas21.locar.controller.PagedResult;
import br.com.andersonfreitas21.locar.controller.veiculos.dtos.VeiculoDTO;
import br.com.andersonfreitas21.locar.controller.veiculos.dtos.VeiculoRequest;
import br.com.andersonfreitas21.locar.controller.veiculos.dtos.VeiculoRequestCriteria;
import br.com.andersonfreitas21.locar.exception.EntityException;
import br.com.andersonfreitas21.locar.exception.EntityNotFoundException;
import br.com.andersonfreitas21.locar.model.veiculo.VeiculoEntity;
import br.com.andersonfreitas21.locar.repository.VeiculoRepository;
import br.com.andersonfreitas21.locar.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VeiculoServiceImpl implements VeiculoService {
    private final VeiculoRepository repository;

    @Override
    public PagedResult<VeiculoDTO> findVeiculos(FindEntityQuery query) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        int pageNo = query.pageNo() > 0 ? query.pageNo() - 1 : 0;
        Pageable pageable = PageRequest.of(pageNo, query.pageSize(), sort);
        Page<VeiculoDTO> page = repository.findVeiculos(pageable);
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
    public PagedResult<VeiculoDTO> findVeiculos(VeiculoRequestCriteria query) {
        List<VeiculoEntity> veiculosFilter = repository.findVeiculosFilter(query);
        return new PagedResult<>(
                convertToDTO(veiculosFilter),
                veiculosFilter.size(),
                1,
                1,
                true,
                true,
                false,
                false
        );
    }

    private List<VeiculoDTO> convertToDTO(List<VeiculoEntity> veiculos) {
        return veiculos
                .stream()
                .map(VeiculoDTO::fromEntity).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VeiculoDTO findById(Integer id) {
        return repository.findVeiculoById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    @Transactional
    public VeiculoDTO create(VeiculoRequest request) {
        var chassi = request.chassi();
        Optional<VeiculoEntity> existingVeiculo = repository.findByChassi(chassi);

        if (existingVeiculo.isPresent()) {
            throw new EntityException(chassi);
        }

        VeiculoEntity veiculo = new VeiculoEntity(
                request.chassi(),
                request.placa(),
                request.idMarca(),
                request.idModelo(),
                request.descricao(),
                request.anoFabricacao(),
                request.kmAtual(),
                Instant.now());

        return VeiculoDTO.fromEntity(repository.save(veiculo));
    }

    @Override
    public void update(Integer id, VeiculoRequest request) {
        VeiculoEntity veiculo = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

        if (Objects.nonNull(request.chassi())) {
            veiculo.setChassi(request.chassi());
        }
        if (Objects.nonNull(request.placa())) {
            veiculo.setPlaca(request.placa());
        }
        if (Objects.nonNull(request.idMarca())) {
            veiculo.setIdMarca(request.idMarca());
        }
        if (Objects.nonNull(request.idModelo())) {
            veiculo.setIdModelo(request.idModelo());
        }
        if (Objects.nonNull(request.descricao())) {
            veiculo.setDescricao(request.descricao());
        }
        if (Objects.nonNull(request.anoFabricacao())) {
            veiculo.setAnoFabricacao(request.anoFabricacao());
        }
        if (Objects.nonNull(request.kmAtual())) {
            veiculo.setKmAtual(request.kmAtual());
        }

        repository.save(veiculo);
    }

    @Override
    public void delete(Integer id) {
        VeiculoEntity veiculo = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        repository.delete(veiculo);
    }
}
