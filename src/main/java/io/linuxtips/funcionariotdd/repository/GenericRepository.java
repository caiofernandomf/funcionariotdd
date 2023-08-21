package io.linuxtips.funcionariotdd.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T,ID> {
    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);

    List<DynamoDBMapper.FailedBatch> saveAll(Iterable<T> objectsToSave);

    void delete(T entity);

    void deleteAll();
}
