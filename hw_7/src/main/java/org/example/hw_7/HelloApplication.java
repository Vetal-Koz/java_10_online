package org.example.hw_7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.hw_7.config.AppConfig;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AppConfig.init(stage, HelloApplication.class);
    }

    public static void main(String[] args) {
        launch();
    }
}