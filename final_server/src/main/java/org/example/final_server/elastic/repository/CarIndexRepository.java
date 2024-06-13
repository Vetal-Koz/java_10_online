package org.example.final_server.elastic.repository;

import org.example.final_server.elastic.document.CarIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarIndexRepository extends ElasticsearchRepository<CarIndex, String> {

    List<CarIndex> findAllByCarInfoContainingIgnoreCase(String carInfo);
}
