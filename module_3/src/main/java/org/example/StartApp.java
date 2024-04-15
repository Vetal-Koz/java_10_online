package org.example;

import org.example.controller.MainController;
import org.example.exception.ExceptionHandler;

public class StartApp implements Runnable{
    @Override
    public void run() {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        MainController mainController = new MainController();
        mainController.start();
    }
}
