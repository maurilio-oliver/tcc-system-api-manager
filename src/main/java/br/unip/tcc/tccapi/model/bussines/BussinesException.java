package br.unip.tcc.tccapi.model.bussines;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

public class BussinesException extends RuntimeException {

    public BussinesException(String message) {
        super(message);
    }


}