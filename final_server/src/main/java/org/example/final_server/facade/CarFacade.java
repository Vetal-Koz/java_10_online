package org.example.final_server.facade;

import org.example.final_server.dto.request.CarRequest;
import org.example.final_server.dto.response.CarResponse;

public interface CarFacade extends CrudFacade<CarRequest, CarResponse>{ }
