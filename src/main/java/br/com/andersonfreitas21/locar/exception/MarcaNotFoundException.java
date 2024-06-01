package br.com.andersonfreitas21.locar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MarcaNotFoundException extends RuntimeException{
}
