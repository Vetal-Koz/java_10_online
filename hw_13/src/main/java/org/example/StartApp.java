package org.example;

import org.example.controller.MainController;
import org.example.exception.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class StartApp implements Runnable{
    @Override
    public void run() {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("org.example");
        appContext.refresh();

        MainController mainController = appContext.getBean(MainController.class);
        mainController.start();
    }
}
