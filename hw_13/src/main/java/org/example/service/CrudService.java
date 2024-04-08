package org.example.service;

import org.example.dto.DataTableRequest;
import org.example.entity.BaseEntity;

import java.util.Collection;

public interface CrudService<E extends BaseEntity> {
    void create(E entity);

    void update(E entity);

    void delete(Long id);

    E findById(Long id);

    Collection<E> findAll();

    Collection<E> findAll(DataTableRequest tableRequest);
}
