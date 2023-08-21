package io.linuxtips.funcionariotdd.testes.mock;

import io.linuxtips.funcionariotdd.model.Funcionario;

public class FuncionarioMock {

    public static Funcionario mockFuncionario(){
        return new Funcionario("456","Everton",7200.D);
    }
}
