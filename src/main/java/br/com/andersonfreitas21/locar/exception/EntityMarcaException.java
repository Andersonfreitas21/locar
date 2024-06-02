package br.com.andersonfreitas21.locar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityMarcaException extends RuntimeException {
    public EntityMarcaException(String nome) {
        super(String.format("Marca com nome= "+nome+" já existe."));
    }
    public static EntityMarcaException of(String nome){
        return new EntityMarcaException(nome);
    }
}
