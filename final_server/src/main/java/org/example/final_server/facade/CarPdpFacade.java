package org.example.final_server.facade;

import org.example.final_server.dto.response.CarPdpResponse;

public interface CarPdpFacade {
    CarPdpResponse findByCar(Long carId);
}
