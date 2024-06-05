package br.com.andersonfreitas21.locar.controller.modelos;


import br.com.andersonfreitas21.locar.controller.PagedResult;
import br.com.andersonfreitas21.locar.controller.modelos.dtos.FindModelosQuery;
import br.com.andersonfreitas21.locar.controller.modelos.dtos.ModeloDTO;
import br.com.andersonfreitas21.locar.controller.modelos.dtos.ModeloRequest;
import br.com.andersonfreitas21.locar.service.ModeloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("api/v1/modelos")
@RequiredArgsConstructor
public class ModeloController {
    private final ModeloService modeloService;

    @GetMapping
    public PagedResult<ModeloDTO> findmodelos(
            @RequestParam(name = "page", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "size", defaultValue = "10") Integer pageSize
    ) {
        FindModelosQuery query = new FindModelosQuery(pageNo, pageSize);
        return modeloService.findModelos(query);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModeloDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(modeloService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ModeloDTO> createmodelo(@RequestBody @Validated ModeloRequest modeloRequest) {
        ModeloDTO modeloDTO = modeloService.create(modeloRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("api/v1/modelos/{id}")
                .buildAndExpand(modeloDTO.id()).toUri();
        return ResponseEntity.created(location).body(modeloDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModeloDTO> updatemodelo(@PathVariable Integer id, @RequestBody @Validated ModeloRequest updatemodeloRequest) {
        modeloService.update(id, updatemodeloRequest.nome());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        modeloService.delete(id);
    }
}
