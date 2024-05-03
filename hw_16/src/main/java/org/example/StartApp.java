package org.example;

import lombok.Getter;
import org.example.controller.MainController;
import org.example.exception.ExceptionHandler;

@Getter
public class StartApp implements Runnable {
    private MainController mainController;

    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        mainController.start();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
