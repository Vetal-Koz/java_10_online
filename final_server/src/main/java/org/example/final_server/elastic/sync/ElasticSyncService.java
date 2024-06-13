package org.example.final_server.elastic.sync;


import lombok.RequiredArgsConstructor;
import org.example.final_server.elastic.document.CarIndex;
import org.example.final_server.elastic.repository.CarIndexRepository;
import org.example.final_server.repository.car.CarVariantRepository;
import org.example.final_server.repository.car.data.CarSearchDto;
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
        carIndexRepository.saveAll(getCarIndexList());
    }

    private List<CarIndex> getCarIndexList(){
        List<CarSearchDto> carSearchDtoList = carVariantRepository.findCarIndexDataList();
        List<CarIndex> carIndices = carSearchDtoList
                .stream()
                .map(cd -> {
                    return CarIndex.builder()
                            .carId(cd.getCar().getId())
                            .carInfo(
                                    cd.getCar().getModel() +
                                            " " + cd.getCar().getBrand().getBrand()
                                            + " " + cd.getCar().getBodyType().getName()
                                            + " " + cd.getFuelType().getType()
                                            + " " + cd.getTransmissionType().getType())
                            .build();
                }
        )
                .toList();
        return carIndices;
    }
}
