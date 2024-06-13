package org.example.final_server.elastic.document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Getter
@Setter
@Builder()
@Document(indexName = "carindex")
public class CarIndex {

    @Id
    private String id;

    @Field(name = "carinfo", type = FieldType.Text)
    private String carInfo;

    @Field(name = "carid", type = FieldType.Long)
    private Long carId;
}
