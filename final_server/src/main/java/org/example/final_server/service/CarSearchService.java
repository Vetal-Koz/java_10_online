package org.example.final_server.service;

import org.example.final_server.elastic.document.CarIndex;

import java.util.List;

public interface CarSearchService {

    List<CarIndex> search(String carInfo);
}
