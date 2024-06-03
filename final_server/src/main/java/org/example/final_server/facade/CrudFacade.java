package org.example.final_server.facade;

import org.example.final_server.dto.request.ApiRequest;
import org.example.final_server.dto.request.DataTableRequest;
import org.example.final_server.dto.response.ApiResponse;
import org.example.final_server.dto.response.DataTableResponse;

import java.util.List;

public interface CrudFacade <REQ extends ApiRequest, RES extends ApiResponse>{
    void create(REQ entity);

    void update(REQ entity, Long id);

    void delete(Long id);

    RES findById(Long id);

    DataTableResponse<RES> findAll(DataTableRequest request);

}
