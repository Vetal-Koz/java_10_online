package org.example.dao;

import java.util.Collection;
import java.util.Optional;

public interface CrudDao<E> {
    void create(E entity);
    void update(E entity);
    void delete(Long id);
    Collection<E> findAll();
    Optional<E> findById(Long id);
}
