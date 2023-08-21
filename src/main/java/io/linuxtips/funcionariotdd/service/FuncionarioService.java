package io.linuxtips.funcionariotdd.service;

import io.linuxtips.funcionariotdd.model.Funcionario;
import io.linuxtips.funcionariotdd.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository=funcionarioRepository;
    }

    public Funcionario criarFuncionario(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }
}
