package br.unip.tcc.tccapi.controller;

import br.unip.tcc.tccapi.model.bussines.BussinesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class BussinesExceptionAdvance {


    @ExceptionHandler(BussinesException.class)
    public ResponseEntity<Object> handleMinhaExcecaoPersonalizada(BussinesException ex) {
        Map<String, Object> message = Map.of("class", ex.getStackTrace()[0].getClassName(), "methodName", ex.getStackTrace()[0].getMethodName(), "message", ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
