package org.example.final_server.type;


import lombok.Getter;

@Getter
public enum DrivetrainType {
    FWD("FWD"),
    RWD("RWD"),
    FourWD("4WD"),
    AWD("AWD");

    private final String type;

    DrivetrainType(String type) {
        this.type = type;
    }
}
