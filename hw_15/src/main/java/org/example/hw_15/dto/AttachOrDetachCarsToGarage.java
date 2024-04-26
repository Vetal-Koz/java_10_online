package org.example.hw_15.dto;

import lombok.Data;

import java.util.List;

@Data
public class AttachOrDetachCarsToGarage {
    private List<Long> carIds;
}
