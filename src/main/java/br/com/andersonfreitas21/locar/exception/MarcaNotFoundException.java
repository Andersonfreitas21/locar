package br.com.andersonfreitas21.locar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MarcaNotFoundException extends RuntimeException {
    public MarcaNotFoundException(Integer id) {
        super(String.format("Marca com id=%d não encotrado", id));
    }
    public static MarcaNotFoundException of(Integer id){
        return new MarcaNotFoundException(id);
    }
}
