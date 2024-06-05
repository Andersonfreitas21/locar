package br.com.andersonfreitas21.locar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Integer id) {
        super(String.format("Marca com id=%d não encotrado", id));
    }
    public static EntityNotFoundException of(Integer id){
        return new EntityNotFoundException(id);
    }
}
