package br.com.andersonfreitas21.locar.controller.veiculos;

import br.com.andersonfreitas21.locar.controller.FindEntityQuery;
import br.com.andersonfreitas21.locar.controller.PagedResult;
import br.com.andersonfreitas21.locar.controller.veiculos.dtos.VeiculoDTO;
import br.com.andersonfreitas21.locar.controller.veiculos.dtos.VeiculoRequest;
import br.com.andersonfreitas21.locar.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/veiculos")
@RequiredArgsConstructor
public class VeiculoController {
    private final VeiculoService service;

    @GetMapping
    public PagedResult<VeiculoDTO> findVeiculos(
            @RequestParam(name = "page", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "size", defaultValue = "10") Integer pageSize
    ) {
        FindEntityQuery query = new FindEntityQuery(pageNo, pageSize);
        return service.findVeiculos(query);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VeiculoDTO> createVeiculo(@RequestBody @Validated VeiculoRequest request) {
        VeiculoDTO VeiculoDTO = service.create(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("api/v1/veiculos/{id}")
                .buildAndExpand(VeiculoDTO.id()).toUri();
        return ResponseEntity.created(location).body(VeiculoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> updateVeiculo(@PathVariable Integer id, @RequestBody @Validated VeiculoRequest request) {
        service.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
