package org.example.final_server.service;

import org.example.final_server.dto.request.DataTableRequest;
import org.example.final_server.entity.BaseEntity;
import org.springframework.data.domain.Page;




public interface CrudService<E extends BaseEntity> {
    void create(E entity);
    void update(E entity);
    E findById(Long id);
    void delete(Long id);
    Page<E> findAll(DataTableRequest request);

}
