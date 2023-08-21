package io.linuxtips.funcionariotdd.controller;

import io.linuxtips.funcionariotdd.model.Funcionario;
import io.linuxtips.funcionariotdd.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService){
        this.funcionarioService=funcionarioService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<Funcionario> salvarFuncionario(@RequestBody Funcionario funcionario){
        return new ResponseEntity<Funcionario>(
                funcionarioService.criarFuncionario(funcionario), HttpStatus.CREATED);
    }

}
