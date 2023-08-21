package io.linuxtips.funcionariotdd.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import io.linuxtips.funcionariotdd.model.Funcionario;
import io.linuxtips.funcionariotdd.repository.FuncionarioRepository;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

@Repository
public class FuncionarioDynamoDbRepository extends DynamoDbRepository<Funcionario,String> implements FuncionarioRepository {

    public FuncionarioDynamoDbRepository(AmazonDynamoDB amazonDynamoDB, Environment env){
        super(amazonDynamoDB,env, Funcionario.class);
    }
}
