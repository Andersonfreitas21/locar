package br.com.andersonfreitas21.locar.controller.modelos;

import br.com.andersonfreitas21.locar.controller.PagedResult;
import br.com.andersonfreitas21.locar.controller.FindEntityQuery;
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
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/modelos")
@RequiredArgsConstructor
public class ModeloController {
    private final ModeloService service;

    @GetMapping
    public PagedResult<ModeloDTO> findmodelos(
            @RequestParam(name = "page", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "size", defaultValue = "10") Integer pageSize
    ) {

        FindEntityQuery query = new FindEntityQuery(pageNo, pageSize);
        return service.findModelos(query);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModeloDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
  
    @GetMapping("marca/{idMarca}")
    public ResponseEntity<List<ModeloDTO>> findByMarcaId(@PathVariable Integer idMarca) {
        return ResponseEntity.ok(service.findByMarca(idMarca));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ModeloDTO> createmodelo(@RequestBody @Validated ModeloRequest modeloRequest) {
        ModeloDTO modeloDTO = service.create(modeloRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("api/v1/modelos/{id}")
                .buildAndExpand(modeloDTO.id()).toUri();
        return ResponseEntity.created(location).body(modeloDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModeloDTO> updatemodelo(@PathVariable Integer id, @RequestBody @Validated ModeloRequest updatemodeloRequest) {
        service.update(id, updatemodeloRequest.nome());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
