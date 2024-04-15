package org.example.config;

public enum FileType {
    STUDENTS_CSV("accountStatement.csv");

    private final String type;

    FileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
