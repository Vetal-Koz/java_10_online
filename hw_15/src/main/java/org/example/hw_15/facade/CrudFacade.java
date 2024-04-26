package org.example.hw_15.facade;


import org.example.hw_15.dto.DataTableRequest;
import org.example.hw_15.dto.request.ApiRequest;
import org.example.hw_15.dto.response.ApiResponse;

import java.util.Collection;

public interface CrudFacade<REQ extends ApiRequest, RES extends ApiResponse> {

    void create(REQ request);

    void update(REQ request, Long id);

    void delete(Long id);

    RES findById(Long id);

    Collection<RES> findAll();

    Collection<RES> findAll(DataTableRequest dataTableRequest);
}
