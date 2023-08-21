package io.linuxtips.funcionariotdd.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import io.linuxtips.funcionariotdd.repository.GenericRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Slf4j
public abstract class DynamoDbRepository<T,ID> implements GenericRepository<T,ID> {

    protected final AmazonDynamoDB amazonDynamoDB;

    private final Environment environment;

    private final Class<T> instanceClass;

    private final DynamoDBMapper dynamoDBMapper;

    public DynamoDbRepository(AmazonDynamoDB amazonDynamoDB, Environment environment, Class<T> instanceClass){
        this.amazonDynamoDB=amazonDynamoDB;
        this.environment=environment;
        this.instanceClass=instanceClass;

        this.dynamoDBMapper= new DynamoDBMapper(amazonDynamoDB);
    }

    @Override
    public List<T> findAll(){
        return dynamoDBMapper.scan(instanceClass,new DynamoDBScanExpression());
    }

    @Override
    public Optional<T> findById(ID id){
        T entity = dynamoDBMapper.load(instanceClass,id);

        if(entity!=null){
            return Optional.of(entity);
        }

        return Optional.empty();
    }

    @Override
    public T save(T entity){
        dynamoDBMapper.save(entity);

        return entity;
    }

    @Override
    public List<DynamoDBMapper.FailedBatch> saveAll(Iterable<T> objectsToSave){
        return dynamoDBMapper.batchSave(objectsToSave);
    }

    @Override
    public void delete(T entity){
        dynamoDBMapper.delete(entity);
    }

    @Override
    public void deleteAll(){
        List<String> activeProfiles = Arrays.stream(environment.getActiveProfiles()).toList();
        if(activeProfiles.contains("test")){
            List<T> entities = findAll();
            if(!CollectionUtils.isEmpty(entities)){
                log.info("limpando tabela");
                dynamoDBMapper.batchDelete(entities);
            }
        }
    }
}
