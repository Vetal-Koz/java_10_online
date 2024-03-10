package org.example.db;

import org.example.entity.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface FileDb<E> {
    void create(E entity);
    void update(E entity);
    void delete(Long id);
    Optional<E> findById(Long id);
    Collection<E> findAll();
}
