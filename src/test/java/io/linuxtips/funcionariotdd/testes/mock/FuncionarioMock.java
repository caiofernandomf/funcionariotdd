package io.linuxtips.funcionariotdd.testes.mock;

import io.linuxtips.funcionariotdd.model.Funcionario;

import java.util.List;

public class FuncionarioMock {

    public static Funcionario mockFuncionario(){
        return new Funcionario("456","Everton",7200.D);
    }

    public static List<Funcionario> mockListFuncionarios(){
        return List.of(
                new Funcionario("123","Caio",4000.D),
                new Funcionario("321","Lucas",4800.D),
                new Funcionario("987","Jefferson",2500.0D)
        );
    }

    public static Funcionario mockFuncionarioAhAlterar(){
        return new Funcionario(null,"Cebolinha",1200000.0D);
    }
}
