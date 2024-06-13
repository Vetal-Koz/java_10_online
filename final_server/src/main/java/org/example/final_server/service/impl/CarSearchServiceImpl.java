package org.example.final_server.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IteratorUtils;
import org.example.final_server.elastic.document.CarIndex;
import org.example.final_server.elastic.repository.CarIndexRepository;
import org.example.final_server.service.CarSearchService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CarSearchServiceImpl implements CarSearchService {

    private final CarIndexRepository carIndexRepository;

    @Override
    public List<CarIndex> search(String carInfo) {
        return IteratorUtils.toList(carIndexRepository.findAllByCarInfoContainingIgnoreCase(carInfo).iterator());
    }
}
