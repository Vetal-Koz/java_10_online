package org.example;

import org.example.controller.FileController;
import org.example.exception.ExceptionHandler;

public class StartApp implements Runnable{
    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        FileController fileController = new FileController();
        fileController.start();
    }
}
