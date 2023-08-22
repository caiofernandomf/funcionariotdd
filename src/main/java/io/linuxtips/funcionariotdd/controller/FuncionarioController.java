package io.linuxtips.funcionariotdd.controller;

import io.linuxtips.funcionariotdd.model.Funcionario;
import io.linuxtips.funcionariotdd.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService){
        this.funcionarioService=funcionarioService;
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Funcionario> salvarFuncionario(@RequestBody Funcionario funcionario){
        return new ResponseEntity<Funcionario>(
                funcionarioService.criarFuncionario(funcionario), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Funcionario>> retornarTodosOsFuncionarios(){
        return ResponseEntity.ok().body(funcionarioService.listarFuncionarios());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Funcionario> retornarFuncionario(@PathVariable String id){
        return ResponseEntity.ok().body(funcionarioService.buscarFuncionarioPorId(id));
    }

}
