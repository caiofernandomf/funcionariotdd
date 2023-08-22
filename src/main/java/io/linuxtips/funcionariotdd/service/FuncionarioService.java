package io.linuxtips.funcionariotdd.service;

import io.linuxtips.funcionariotdd.model.Funcionario;
import io.linuxtips.funcionariotdd.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository=funcionarioRepository;
    }

    public Funcionario criarFuncionario(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarFuncionarios() {
        return  funcionarioRepository.findAll();
    }

    public Funcionario buscarFuncionarioPorId(String id) {
        Optional<Funcionario> optionalFuncionario =
                funcionarioRepository.findById(id);
        
            return optionalFuncionario.get();


    }
}
