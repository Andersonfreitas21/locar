package br.com.andersonfreitas21.locar.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
@Slf4j
public class TesteApi {
    @GetMapping
    public ResponseEntity<String> getTestAPI(){
        log.info("Log locar request api");
        return ResponseEntity.ok("Teste API");
    }
}
