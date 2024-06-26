package org.example.final_server.repository.car;

import org.example.final_server.entity.car.CarVariant;
import org.example.final_server.repository.BaseRepository;
import org.example.final_server.repository.car.data.CarSearchDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarVariantRepository extends BaseRepository<CarVariant> {
    List<CarVariant> findByCarId(Long carId);
    @Query(value = "select cv.carEngine.id from CarVariant cv where cv.car.id = ?1")
    List<Long> findCarEngineIdByCarId(Long carId);

    @Query(value = "select distinct " +
            "new org.example.final_server.repository.car.data" +
            ".CarSearchDto(cv.car, cv.transmission, cv.carEngine.typeOfFuel)" +
            " from CarVariant cv")
    List<CarSearchDto> findCarIndexDataList();

}
