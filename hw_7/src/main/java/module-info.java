module org.example.hw_7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.hw_7 to javafx.fxml;
    exports org.example.hw_7;
    exports org.example.hw_7.controller;
    exports org.example.hw_7.entity;
    exports org.example.hw_7.service;
    exports org.example.hw_7.db;

    opens org.example.hw_7.controller to javafx.fxml;
}