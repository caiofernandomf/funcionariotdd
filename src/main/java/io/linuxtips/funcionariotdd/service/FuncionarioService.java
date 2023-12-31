package io.linuxtips.funcionariotdd.service;

import io.linuxtips.funcionariotdd.exception.FuncionarioNaoEncontradoException;
import io.linuxtips.funcionariotdd.model.Funcionario;
import io.linuxtips.funcionariotdd.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

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

    public Funcionario buscarFuncionarioPorId(String id) throws FuncionarioNaoEncontradoException{

            return funcionarioRepository
                    .findById(id)
                    .map(funcionario -> funcionario)
                    .orElseThrow(()->new FuncionarioNaoEncontradoException(id));

    }

    public String excluirFuncionarioPorId(String id) {
        funcionarioRepository.delete(buscarFuncionarioPorId(id));
        return "Funcionário excluído com sucesso";
    }

    public Funcionario alterarFuncionarioPorId(String id,Funcionario funcionario) {
        Funcionario funcionarioAsalvar = buscarFuncionarioPorId(id);
        funcionarioAsalvar.setNome(funcionario.getNome());
        funcionarioAsalvar.setRemuneracao(funcionario.getRemuneracao());

        return funcionarioRepository.save(funcionarioAsalvar);
    }
}
