package org.example.final_server.elastic.sync;


import lombok.RequiredArgsConstructor;
import org.example.final_server.elastic.document.CarIndex;
import org.example.final_server.elastic.repository.CarIndexRepository;
import org.example.final_server.repository.car.CarVariantRepository;
import org.example.final_server.repository.car.data.CarIndexData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElasticSyncService {

    private final CarIndexRepository carIndexRepository;
    private final CarVariantRepository carVariantRepository;

    @Scheduled(cron = "*/10 * * * * *")
    public void updateElasticData(){
        carIndexRepository.deleteAll();
        System.out.println("ElasticSyncService.updateElasticData");
        List<CarIndexData> carIndexDataList = carVariantRepository.findCarIndexDataList();
        List<CarIndex> carIndices = carIndexDataList.stream().map(cd -> {
            return CarIndex.builder()
                    .carId(cd.getCar().getId())
                    .carInfo(cd.getCar() + " " + cd.getFuelType() + " " + cd.getTransmissionType())
                    .build();
            }
        ).toList();
        carIndexRepository.saveAll(carIndices);
    }
}
