package io.linuxtips.funcionariotdd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Funcionário não encontrado")
public class FuncionarioNaoEncontradoException extends RuntimeException{
    public FuncionarioNaoEncontradoException(){
        super();
    }

    public FuncionarioNaoEncontradoException(String id){
        super("Funcionário com o ID "+id+" não encontrado na base");
    }
}
