package io.linuxtips.funcionariotdd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FuncionarioExceptionHandler {

    @ExceptionHandler(FuncionarioNaoEncontradoException.class)
    public ResponseEntity<Object> handleFuncionarioNaoEncontradoException(
            RuntimeException runtimeException
    ){
        return
                ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(runtimeException.getMessage()+
                                " "+runtimeException.getClass());
    }
}
