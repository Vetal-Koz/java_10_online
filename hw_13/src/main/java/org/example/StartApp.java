package org.example;

import org.example.controller.MainController;
import org.example.exception.ExceptionHandler;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class StartApp implements Runnable {
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
