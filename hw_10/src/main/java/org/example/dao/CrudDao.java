package org.example.dao;

import org.example.entity.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface CrudDao<E extends BaseEntity> {
    void create(E entity);

    void update(E entity);

    void delete(Long id);

    boolean existById(Long id);

    Collection<E> findAll();

    Optional<E> findById(Long id);
}
