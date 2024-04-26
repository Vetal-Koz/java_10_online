package org.example.hw_15.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTableRequest {
    private int size;
    private int page;

    public DataTableRequest() {
        this.page = 1;
        this.size = 10;
    }

}
