package br.com.andersonfreitas21.locar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityException extends RuntimeException {
    public EntityException(String nome) {
        super("Objeto " + nome + "já existente na base de dados");
    }

    public static EntityException of(String nome) {
        return new EntityException(nome);
    }
}
