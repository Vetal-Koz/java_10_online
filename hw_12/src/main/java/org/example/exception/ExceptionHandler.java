package org.example.exception;

import org.example.StartApp;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(e.getMessage());
        new Thread(new StartApp()).start();

    }
}
