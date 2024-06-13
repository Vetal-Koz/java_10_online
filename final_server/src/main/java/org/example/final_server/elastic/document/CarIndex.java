package org.example.final_server.elastic.document;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Builder(toBuilder = true)
@Document(indexName = "carIndex")
public class CarIndex {

    @Id
    private String id;

    @Field(name = "carInfo", type = FieldType.Text)
    private String carInfo;

    @Field(name = "carId", type = FieldType.Long)
    private Long carId;
}
