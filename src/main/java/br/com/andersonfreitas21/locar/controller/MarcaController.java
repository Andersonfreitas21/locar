package br.com.andersonfreitas21.locar.controller;

import br.com.andersonfreitas21.locar.controller.dto.*;
import br.com.andersonfreitas21.locar.service.MarcaService;
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
@RequestMapping("api/v1/marcas")
@RequiredArgsConstructor
public class MarcaController {
    private final MarcaService marcaService;

    @GetMapping
    public PagedResult<MarcaDTO> findMarcas(
            @RequestParam(name = "page", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "size", defaultValue = "10") Integer pageSize
    ) {
        FindMarcasQuery query = new FindMarcasQuery(pageNo, pageSize);
        return marcaService.findMarcas(query);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(marcaService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MarcaDTO> createMarca(@RequestBody @Validated MarcaRequest marcaRequest) {
        MarcaDTO marcaDTO = marcaService.create(marcaRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("api/v1/marcas/{id}")
                .buildAndExpand(marcaDTO.id()).toUri();
        return ResponseEntity.created(location).body(marcaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaDTO> updateMarca(@PathVariable Integer id, @RequestBody @Validated UpdateMarcaRequest updateMarcaRequest) {
        marcaService.update(id, updateMarcaRequest.nome());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        marcaService.delete(id);
    }
}
